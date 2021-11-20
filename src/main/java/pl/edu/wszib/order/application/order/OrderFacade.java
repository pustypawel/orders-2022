package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderApiResult;
import pl.edu.wszib.order.api.order.OrderError;
import pl.edu.wszib.order.api.order.OrderItemAddApi;
import pl.edu.wszib.order.application.product.Product;
import pl.edu.wszib.order.application.product.ProductFacade;
import pl.edu.wszib.order.application.product.ProductId;

import java.util.Optional;

@AllArgsConstructor
public class OrderFacade {
    private final OrderRepository orderRepository;
    private final ProductFacade productFacade;

    public OrderApi create() {
        final Order order = Order.create();
        orderRepository.save(order);
        return order.toApi();
    }

    public Optional<OrderApi> findById(final String id) {
        return findById(OrderId.of(id));
    }

    public Optional<OrderApi> findById(final OrderId id) {
        return orderRepository.findById(id)
                .map(Order::toApi);
    }

    public OrderApiResult addItem(final String orderId,
                                  final OrderItemAddApi itemToAdd) {
        return orderRepository.findById(orderId)
                .map(order -> addItem(order, itemToAdd))
                .orElseGet(() -> OrderApiResult.failure(OrderError.ORDER_NOT_FOUND));
    }

    //TODO Refactor
    private OrderApiResult addItem(final Order order,
                                   final OrderItemAddApi itemToAdd) {
        final String productId = itemToAdd.getProductId();
        final Optional<Product> product = productFacade.findById(productId);
        if (product.isEmpty()) {
            return OrderApiResult.failure(OrderError.PRODUCT_NOT_FOUND);
        }
        final Integer quantity = itemToAdd.getQuantity();
        final OrderItem orderItem = OrderItem.create(product.get(), quantity);
        final Order modifiedOrder = order.addItem(orderItem);
        return OrderApiResult.success(orderRepository.save(modifiedOrder).toApi());
    }
}
