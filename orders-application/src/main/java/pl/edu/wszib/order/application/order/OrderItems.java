package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import lombok.ToString;
import pl.edu.wszib.order.api.order.OrderItemApi;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@ToString
public class OrderItems {
    private final Set<OrderItem> items;

    public static OrderItems create() {
        return new OrderItems(new HashSet<>());
    }

    static OrderItems fromApi(final Set<OrderItemApi> itemsApi) {
        final Set<OrderItem> items = itemsApi.stream()
                .map(OrderItem::fromApi)
                .collect(Collectors.toSet());
        return new OrderItems(items);
    }

    OrderItems add(final OrderItem orderItem) {
        items.add(orderItem);
        return new OrderItems(items);
    }

    OrderItems remove(final String productId) {
        items.removeIf(item -> item.hasProductId(productId));
        return new OrderItems(items);
    }

    public Set<OrderItemApi> toApi() {
        return items.stream()
                .map(OrderItem::toApi)
                .collect(Collectors.toSet());
    }

    BigDecimal amount() {
        return items.stream()
                .map(OrderItem::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
