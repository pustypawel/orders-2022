package pl.edu.wszib.order.api.order;

import lombok.Value;
import pl.edu.wszib.order.api.product.ProductApi;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Value
public class OrderItemApi {

    @NotNull
    private final ProductApi product;

    @NotNull
    @Positive
    private final Integer quantity;

    @NotNull
    @Positive
    private final BigDecimal amount;

    public boolean hasProductId(final String productId) {
        return Objects.equals(this.product.getId(), productId);
    }

}
