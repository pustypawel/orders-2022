package pl.edu.wszib.order.infrastructure.persistance.orders;

import pl.edu.wszib.order.application.order.Order;
import pl.edu.wszib.order.application.order.OrderId;
import pl.edu.wszib.order.application.order.OrderRepository;

import java.util.Collection;
import java.util.Optional;

public class SpringDataJpaOrderRepository implements OrderRepository {
    private final OrderDao orderDao;

    public SpringDataJpaOrderRepository(final OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        final Optional<OrderEntity> orderEntity = orderDao.findById(id.asBasicType());
        return Optional.empty();
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public Collection<Order> findAll() {
        return null;
    }
}
