package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import lombok.ToString;
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
        return BigDecimal.valueOf(0);
    }

    public static OrderItem create() {
        return new OrderItem(
                new Product(    //TODO ProductFacade
                        ProductId.create(),
                        "Czekolada",
                        BigDecimal.valueOf(2)
                ),
                1
        );
    }
}
