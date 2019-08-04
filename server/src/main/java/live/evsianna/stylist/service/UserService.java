package live.evsianna.stylist.service;

import live.evsianna.stylist.controller.model.UserOrderDTO;
import live.evsianna.stylist.controller.model.UsersRequestDTO;
import live.evsianna.stylist.model.Order;
import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.projection.UserProjection;
import live.evsianna.stylist.repository.UserRepository;
import live.evsianna.stylist.service.interfaces.IOrderService;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import live.evsianna.stylist.service.interfaces.IUserService;

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
    public Page<UserProjection> findAll(final UsersRequestDTO request) {
        final Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return userRepository.findAllUsers(pageable);
    }

    @Override
    public UserOrderDTO saveUserAndOrder(final UserOrderDTO dto) {
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

    @Transactional
    public User save(final User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(final String id) {
        throw new RuntimeException("Method not implemented.");
    }

    @Override
    @Transactional
    public void deleteById(final String id) {
        throw new RuntimeException("Method not implemented.");
    }

    @Override
    @Transactional
    public User update(User user) {
        throw new RuntimeException("Method not implemented.");
    }

}
