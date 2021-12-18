package pl.edu.wszib.order.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Set;

@Value
public class OrderApi {
    @NotBlank
    private final String id;
    @NotNull
    private final OrderState state;
    @NotEmpty
    private final Set<@Valid OrderItemApi> items;
    @NotNull
    private final BigDecimal amount;

    public boolean notContainsProduct(final String productId) {
        return !containsProduct(productId);
    }

    public boolean containsProduct(final String productId) {
        return items.stream()
                .anyMatch(item -> item.hasProductId(productId));
    }

}
