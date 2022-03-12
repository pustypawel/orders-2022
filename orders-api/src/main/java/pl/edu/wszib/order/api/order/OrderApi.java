package pl.edu.wszib.order.api.order;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Value;
import pl.edu.wszib.order.api.product.ProductApi;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<ProductApi> getProducts() {
        return items.stream()
                .map(OrderItemApi::getProduct)
                .collect(Collectors.toSet());
    }

}
