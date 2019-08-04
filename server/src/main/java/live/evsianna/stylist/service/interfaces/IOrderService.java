package live.evsianna.stylist.service.interfaces;

import live.evsianna.stylist.controller.model.UsersRequestDTO;
import org.springframework.data.domain.Page;
import live.evsianna.stylist.model.Order;

public interface IOrderService {

    Page<Order> findAll(final UsersRequestDTO request);

    Order findById(final String id);

    Order save(final Order order);

    void deleteById(final String id);

    Order update(final Order order);

}
