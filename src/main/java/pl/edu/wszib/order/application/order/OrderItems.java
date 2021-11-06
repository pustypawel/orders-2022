package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@ToString
public class OrderItems {
    private final Set<OrderItem> items;

    public static OrderItems create() {
        return new OrderItems(new HashSet<>());
    }

    OrderItems add(final OrderItem orderItem) {
        items.add(orderItem);
        return new OrderItems(items);
    }
}
