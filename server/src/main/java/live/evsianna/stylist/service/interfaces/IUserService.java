package live.evsianna.stylist.service.interfaces;

import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.dto.UserDTO;
import live.evsianna.stylist.model.dto.UserFavorDTO;
import live.evsianna.stylist.model.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    Page<UserProjection> findAll(boolean enabled, int page, int size);

    UserProjection findProjectionById(final String id);

    User findById(final String id);

    UserFavorDTO saveNewUserWithFavor(final UserFavorDTO dto);

    void saveSimple(final User user);

    User save(final User user);

    void deleteById(final String id);

    void setIsEnabledById(boolean enabled, final String id);

    UserDTO update(final UserDTO user);

}
