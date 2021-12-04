package pl.edu.wszib.order.api.order;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Objects;

@Value
public class OrderItemApi {
    @NotBlank
    private final String productId;

    @NotBlank
    @Min(2)
    @Max(50)
    private final String name;

    @NotNull
    @Positive
    private final BigDecimal price;

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
