package live.evsianna.stylist.repository;

import live.evsianna.stylist.model.Favor;
import live.evsianna.stylist.model.projection.FavorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FavorRepository extends JpaRepository<Favor, String> {

    Set<FavorProjection> findAllByOrderByCreatedDesc();

}
