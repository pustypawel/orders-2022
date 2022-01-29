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

    private OrderItem(final String productId,
                      final ProductApi productApi,
                      final Integer quantity) {
        this.productId = productId;
        this.productApi = productApi;
        this.quantity = quantity;
        this.amount = calculateAmount();
    }

    private BigDecimal calculateAmount() {
        return BigDecimal.valueOf(0);   //TODO Impl
    }

    public static OrderItem create(final ProductApi productApi,
                                   final Integer quantity) {
        return new OrderItem(productApi.getId(), productApi, quantity);
    }

    public boolean hasProductId(final String productId) {
        return this.productId.equals(productId);
    }

    public OrderItemApi toApi() {
        return new OrderItemApi(productId, productApi, quantity, amount);
    }
}

/*
#TODO Przygotować API dla zamówień:
  # create order (POST)
  # find order (GET)
  # add item (POST /orders/{orderId}/items)
  # remove item (DELETE /orders/{orderId}/items/{productId})
 */