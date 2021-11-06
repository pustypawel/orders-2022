package pl.edu.wszib.order.application.order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(OrderId id);

    Order save(Order order);
}
