package live.evsianna.stylist.service;

import live.evsianna.stylist.controller.model.UsersRequestDTO;
import live.evsianna.stylist.repository.OrderRepository;
import live.evsianna.stylist.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import live.evsianna.stylist.model.Order;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Order> findAll(final UsersRequestDTO request) {
        throw new RuntimeException("Method not implemented.");
    }

    @Override
    public Order findById(final String id) {
        throw new RuntimeException("Method not implemented.");
    }

    @Override
    public Order save(final Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(final String id) {
        throw new RuntimeException("Method not implemented.");
    }

    @Override
    public Order update(final Order order) {
        throw new RuntimeException("Method not implemented.");
    }

}
