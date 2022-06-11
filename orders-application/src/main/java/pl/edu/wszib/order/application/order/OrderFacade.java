package pl.edu.wszib.order.application.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderApiResult;
import pl.edu.wszib.order.api.order.OrderError;
import pl.edu.wszib.order.api.product.ProductApi;
import pl.edu.wszib.order.application.product.ProductFacade;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderFacade {
    private final OrderRepository orderRepository;
    private final ProductFacade productFacade;

    public OrderApiResult create() {
        final Order order = Order.create();
        orderRepository.save(order);
        return OrderApiResult.success(order.toApi());
    }

    public OrderApi findByIdOrThrow(final String orderId) {
        final OrderApiResult result = findById(orderId);
        if (result.isFailure()) {
            throw new NoSuchElementException();
        }
        return result.getOrder();
    }

    public OrderApiResult findById(final String id) {
        return findById(OrderId.of(id));
    }

    public Collection<OrderApi> findAll(final Integer page,
                                        final Integer size,
                                        final String sort) {
        return orderRepository.findAll(page, size, sort)
                .stream()
                .map(Order::toApi)
                .collect(Collectors.toSet());
    }

    public OrderApiResult findById(final OrderId id) {
        return orderRepository.findById(id)
                .map(Order::toApi)
                .map(OrderApiResult::success)
                .orElseGet(() -> OrderApiResult.failure(OrderError.ORDER_NOT_FOUND));
    }

    public OrderApiResult addItem(final String orderId,
                                  final String productId,
                                  final Integer quantity) {
        return orderRepository.findById(orderId)
                .map(order -> addItem(order, productId, quantity))
                .orElseGet(() -> OrderApiResult.failure(OrderError.ORDER_NOT_FOUND));
    }
    //TODO Refactor

    private OrderApiResult addItem(final Order order,
                                   final String productId,
                                   final Integer quantity) {
        final Optional<ProductApi> product = productFacade.findById(productId);
        if (product.isEmpty()) {
            return OrderApiResult.failure(OrderError.PRODUCT_NOT_FOUND);
        }
        //FIXME if product already exists increase quantity only
        final OrderItem orderItem = OrderItem.create(product.get(), quantity);
        final Order modifiedOrder = order.addItem(orderItem);
        return OrderApiResult.success(orderRepository.save(modifiedOrder).toApi());
    }

    public OrderApiResult removeItem(final String orderId,
                                     final String productId) {
        return orderRepository.findById(orderId)
                .map(order -> order.removeItem(productId))
                .map(orderRepository::save)
                .map(Order::toApi)
                .map(OrderApiResult::success)
                .orElseGet(() -> OrderApiResult.failure(OrderError.ORDER_NOT_FOUND));
    }
}
