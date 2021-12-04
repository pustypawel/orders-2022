package pl.edu.wszib.order.api.order;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Objects;

@Value
public class OrderItemApi {
    private final String productId;
    private final String name;
    private final BigDecimal price;
    private final Integer quantity;
    private final BigDecimal amount;

    public boolean hasProductId(final String productId) {
        return Objects.equals(this.productId, productId);
    }
}
