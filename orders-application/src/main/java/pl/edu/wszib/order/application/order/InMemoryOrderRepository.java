package pl.edu.wszib.order.application.order;

import pl.edu.wszib.order.api.PageApi;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<OrderId, Order> orders = new HashMap<>();

    @Override
    public Order save(final Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Collection<Order> findAll(PageApi pageApi) {
        return orders.values();
    }

    @Override
    public Optional<Order> findById(final OrderId id) {
        return Optional.ofNullable(orders.get(id));
    }
}
