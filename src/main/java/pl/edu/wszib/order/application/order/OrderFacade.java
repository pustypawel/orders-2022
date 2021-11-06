package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class OrderFacade {
    private final OrderRepository orderRepository;

    public Order create() {
        final Order order = Order.create();
        orderRepository.save(order);
        return order;
    }

    public Optional<Order> findById(final OrderId id) {
        return orderRepository.findById(id);
    }

    public Optional<Order> addItem(final OrderId orderId,
                                   final OrderItem orderItem) {
        return orderRepository.findById(orderId)
                .map(order -> order.addItem(orderItem))
                .map(orderRepository::save);
    }
}
