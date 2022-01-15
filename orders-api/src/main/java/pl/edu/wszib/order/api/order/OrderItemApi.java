package pl.edu.wszib.order.api.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;
import pl.edu.wszib.order.api.product.ProductApi;

import java.math.BigDecimal;
import java.util.Objects;

@Value
public class OrderItemApi {
    @NotBlank
    private final String productId;

    @NotNull
    private final ProductApi product;

    @NotNull
    @Positive
    private final Integer quantity;

    @NotNull
    @Positive
    private final BigDecimal amount;

    public boolean hasProductId(final String productId) {
        return Objects.equals(this.productId, productId);
    }

}
