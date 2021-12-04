package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import lombok.ToString;
import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderItemApi;
import pl.edu.wszib.order.application.product.Product;
import pl.edu.wszib.order.application.product.ProductId;

import java.math.BigDecimal;

@AllArgsConstructor
@ToString
public class OrderItem {
    private final Product product;
    private final Integer quantity;
    private final BigDecimal amount;

    private OrderItem(final Product product,
                      final Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.amount = calculateAmount();
    }

    private BigDecimal calculateAmount() {
        return BigDecimal.valueOf(0);   //TODO Impl
    }

    public static OrderItem create(final Product product,
                                   final Integer quantity) {
        return new OrderItem(product, quantity);
    }

    public boolean hasProductId(final String productId) {
        return product.hasId(productId);
    }

    public OrderItemApi toApi() {
        return new OrderItemApi(product.getId().asBasicType(), product.getName(), product.getPrice(), quantity, amount);
    }
}
