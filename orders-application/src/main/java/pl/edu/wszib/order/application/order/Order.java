package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderState;

import java.math.BigDecimal;

@ToString
public class Order {
    @Getter
    private final OrderId id;
    private final OrderState state;
    private final OrderItems items;
    private final BigDecimal amount;

    public Order(OrderId id, OrderState state, OrderItems items) {
        this.id = id;
        this.state = state;
        this.items = items;
        this.amount = items.amount();
    }

    public static Order create() {
        return new Order(
                OrderId.create(),
                OrderState.CREATED,
                OrderItems.create()
        );
    }

    public static Order fromApi(final OrderApi orderApi) {
        return new Order(
                OrderId.of(orderApi.getId()),
                orderApi.getState(),
                OrderItems.fromApi(orderApi.getItems())
        );
    }

    public Order addItem(final OrderItem orderItem) {
        return new Order(id, state, items.add(orderItem));
    }

    public Order removeItem(final String productId) {
        return new Order(id, state, items.remove(productId));
    }

    public OrderApi toApi() {
        return new OrderApi(id.asBasicType(), state, items.toApi(), amount);
    }
}
