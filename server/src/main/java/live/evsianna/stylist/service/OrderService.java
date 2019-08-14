package live.evsianna.stylist.service;

import live.evsianna.stylist.model.Order;
import live.evsianna.stylist.model.projection.OrderProjection;
import live.evsianna.stylist.repository.OrderRepository;
import live.evsianna.stylist.service.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<OrderProjection> findAll(int page, int size) {
        final PageRequest pageable = PageRequest.of(page, size);
        return orderRepository.findAllByOrderByCreatedDesc(pageable);
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
    public void deleteByUserId(final String id) {
        orderRepository.deleteByUserId(id);
    }

    @Override
    public Order update(final Order order) {
        throw new RuntimeException("Method not implemented.");
    }

}
