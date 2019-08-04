package live.evsianna.stylist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import live.evsianna.stylist.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
