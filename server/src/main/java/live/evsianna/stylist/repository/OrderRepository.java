package live.evsianna.stylist.repository;

import live.evsianna.stylist.model.Order;
import live.evsianna.stylist.model.projection.OrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    Page<OrderProjection> findAllByOrderByCreatedDesc(final Pageable pageable);

    void deleteByUserId(final String id);
}
