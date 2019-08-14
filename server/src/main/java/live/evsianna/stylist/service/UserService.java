package live.evsianna.stylist.service;

import live.evsianna.stylist.exception.UserNotCreatedException;
import live.evsianna.stylist.model.dto.UserDTO;
import live.evsianna.stylist.model.dto.UserOrderDTO;
import live.evsianna.stylist.exception.UserNotFoundException;
import live.evsianna.stylist.model.Order;
import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.projection.UserProjection;
import live.evsianna.stylist.repository.UserRepository;
import live.evsianna.stylist.service.interfaces.IOrderService;
import live.evsianna.stylist.service.interfaces.IUserService;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Data
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final IOrderService orderService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AppMailService appMailService;

    @Autowired
    public UserService(UserRepository userRepository, IOrderService orderService) {
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '" + email + "' - Not Found."));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserProjection> findAll(boolean enabled, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllByEnabledOrderByCreatedDesc(enabled, pageable);
    }

    @Override
    public UserOrderDTO saveNewUserWithOrder(final UserOrderDTO dto) {
        appMailService.sendEmailToStylist(dto);
        final User user = dto.getUser();

        user.setPassword(RandomStringUtils.randomAlphanumeric(7));
        appMailService.sendEmailToClient(dto);

        final Order order = dto.getOrder();
        order.setUser(user);

        final User userSaved = save(user);
        final Order orderSaved = orderService.save(order);
        return new UserOrderDTO(userSaved, orderSaved);
    }

    @Override
    @Transactional
    public User save(final User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void saveSimple(final User user) {
        user.setPassword(RandomStringUtils.randomAlphanumeric(7));
        appMailService.sendEmailToClient(user);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        if (user.getId() == null) {
            throw new UserNotCreatedException("Some problem occurred while creating a user." + user.toString());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserProjection findProjectionById(final String id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with userId '" + id + "' - Not found!"));
    }

    @NotNull
    @Override
    @Transactional(readOnly = true)
    public User findById(final String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with userId '" + id + "' - Not found!"));
    }

    @Transactional
    public void setIsEnabledById(boolean enabled, final String id) {
        final User user = findById(id);
        user.setEnabled(enabled);
    }

    @Override
    @Transactional
    public void deleteById(final String id) {
        final User user = findById(id);
        user.setRoles(null);
        orderService.deleteByUserId(user.getId());
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO user) {
        throw new RuntimeException("Method not implemented.");
    }

}
