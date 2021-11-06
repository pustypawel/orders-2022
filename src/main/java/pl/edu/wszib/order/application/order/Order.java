package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@ToString
public class Order {
    @Getter
    private final OrderId id;
    private final OrderState state;
    private final OrderItems items;
    private final BigDecimal amount;

    public static Order create() {
        return new Order(
                OrderId.create(),
                OrderState.CREATED,
                OrderItems.create(),
                BigDecimal.ZERO
        );
    }

    public Order addItem(final OrderItem orderItem) {
        return new Order(id, state, items.add(orderItem), amount);
    }
}
