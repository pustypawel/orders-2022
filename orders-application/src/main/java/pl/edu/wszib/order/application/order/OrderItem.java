package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import lombok.ToString;
import pl.edu.wszib.order.api.order.OrderItemApi;
import pl.edu.wszib.order.api.product.ProductApi;

import java.math.BigDecimal;

@AllArgsConstructor
@ToString
public class OrderItem {
    private final String productId;
    private final ProductApi productApi;
    private final Integer quantity;
    private final BigDecimal amount;

    private OrderItem(final ProductApi productApi,
                      final Integer quantity) {
        this.productId = productApi.getId();
        this.productApi = productApi;
        this.quantity = quantity;
        this.amount = calculateAmount();
    }

    private BigDecimal calculateAmount() {
        return productApi.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public static OrderItem create(final ProductApi productApi,
                                   final Integer quantity) {
        return new OrderItem(productApi, quantity);
    }

    public static OrderItem fromApi(final OrderItemApi item) {
        return new OrderItem(
                item.getProduct(),
                item.getQuantity()
        );
    }

    public boolean hasProductId(final String productId) {
        return this.productId.equals(productId);
    }

    BigDecimal amount() {
        return amount;
    }

    public OrderItemApi toApi() {
        return new OrderItemApi(productApi, quantity, amount);
    }
}
