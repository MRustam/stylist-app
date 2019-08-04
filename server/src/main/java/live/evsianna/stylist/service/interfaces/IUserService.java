package live.evsianna.stylist.service.interfaces;

import live.evsianna.stylist.controller.model.UserOrderDTO;
import live.evsianna.stylist.controller.model.UsersRequestDTO;
import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    Page<UserProjection> findAll(final UsersRequestDTO request);

    User findById(final String id);

    UserOrderDTO saveUserAndOrder(final UserOrderDTO dto);

    void deleteById(final String id);

    User update(final User user);

}
