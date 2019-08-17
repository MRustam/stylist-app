package live.evsianna.stylist.service;

import live.evsianna.stylist.exception.UserNotCreatedException;
import live.evsianna.stylist.exception.UserNotFoundException;
import live.evsianna.stylist.model.Favor;
import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.dto.UserDTO;
import live.evsianna.stylist.model.dto.UserFavorDTO;
import live.evsianna.stylist.model.projection.UserProjection;
import live.evsianna.stylist.repository.UserRepository;
import live.evsianna.stylist.service.interfaces.IFavorService;
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

    private final IFavorService iFavorService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AppMailService appMailService;

    @Autowired
    public UserService(UserRepository userRepository, IFavorService iFavorService) {
        this.userRepository = userRepository;
        this.iFavorService = iFavorService;
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
    public UserFavorDTO saveNewUserWithFavor(final UserFavorDTO dto) {
        dto.getUser().setPassword(RandomStringUtils.randomAlphanumeric(7));
        appMailService.sendEmailToStylist(dto);
        appMailService.sendEmailToConsumer(dto);
        final User user = dto.getUser();
        final Favor favor = dto.getFavor();
        user.addFavor(favor);
        this.save(user);
        iFavorService.save(favor);
        return new UserFavorDTO(user, favor);
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
        appMailService.sendEmailToConsumer(user);
        this.save(user);
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
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO user) {
        throw new RuntimeException("Method not implemented.");
    }

}
