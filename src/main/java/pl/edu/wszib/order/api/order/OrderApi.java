package pl.edu.wszib.order.api.order;

import lombok.Value;
import pl.edu.wszib.order.application.order.OrderState;

import java.math.BigDecimal;
import java.util.Set;

@Value
public class OrderApi {
    private final String id;
    private final OrderState state;
    private final Set<OrderItemApi> items;
    private final BigDecimal amount;

    public boolean containsProduct(final String productId) {
        return items.stream()
                .map(OrderItemApi::getProductId)
                .anyMatch(itemProductId -> itemProductId.equals(productId));
    }
}
