package live.evsianna.stylist.service.interfaces;

import live.evsianna.stylist.model.Order;
import live.evsianna.stylist.model.projection.OrderProjection;
import org.springframework.data.domain.Page;

public interface IOrderService {

    Page<OrderProjection> findAll(int page, int size);

    Order findById(final String id);

    Order save(final Order order);

    void deleteById(final String id);

    void deleteByUserId(final String id);

    Order update(final Order order);

}
