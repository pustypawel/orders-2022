package pl.edu.wszib.order.application.order;

import pl.edu.wszib.order.api.PageApi;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository {

    default Optional<Order> findById(String id) {
        return findById(OrderId.of(id));
    }

    Optional<Order> findById(OrderId id);

    Order save(Order order);

    Collection<Order> findAll(PageApi pageApi);
}
