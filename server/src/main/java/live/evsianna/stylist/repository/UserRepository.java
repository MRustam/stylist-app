package live.evsianna.stylist.repository;

import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Page<UserProjection> findAllByEnabledOrderByCreatedDesc(boolean enabled, final Pageable pageable);

    Optional<UserProjection> findUserById(final String id);

    Optional<User> findByEmail(final String email);

}
