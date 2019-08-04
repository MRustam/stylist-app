package live.evsianna.stylist.repository;

import live.evsianna.stylist.model.User;
import live.evsianna.stylist.model.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(final String email);

    @Query("SELECT u FROM User u")
    Page<UserProjection> findAllUsers(final Pageable pageable);

}
