package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderState;

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
                BigDecimal.ZERO //TODO Impl
        );
    }

    public Order addItem(final OrderItem orderItem) {
        return new Order(id, state, items.add(orderItem), amount);  //TODO Impl amount
    }

    public Order removeItem(final String productId) {
        return new Order(id, state, items.remove(productId), amount);
    }

    public OrderApi toApi() {
        return new OrderApi(id.asBasicType(), state, items.toApi(), amount);
    }
}
