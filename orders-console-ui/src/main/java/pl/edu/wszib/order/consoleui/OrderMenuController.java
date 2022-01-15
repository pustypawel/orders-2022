package pl.edu.wszib.order.consoleui;

import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderState;
import pl.edu.wszib.order.api.product.ProductApi;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

class OrderMenuController {
    private final OrderMenuView view;

    public OrderMenuController(final OrderMenuView view) {
        this.view = view;
    }

    OrderApi handle(final OrderMenuOption option) {
        switch (option) {
            case CREATE_ORDER:
                return createOrder();
            case GET_ORDER:
                return getOrder();
            case ADD_ITEM_TO_ORDER:
                return addItem();
            case REMOVE_ITEM_FROM_ORDER:
                return removeItem();
            case EXIT:
                return exit();
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }
    }

    private OrderApi createOrder() {
//        final OrderItemApi item = new OrderItemApi(UUID.randomUUID().toString(), "Testowy produkt", BigDecimal.valueOf(10), 1);
        //TODO wywołać logikę aplikacyjną
        return new OrderApi(UUID.randomUUID().toString(), OrderState.CREATED, Set.of(), BigDecimal.ZERO);
    }

    private OrderApi getOrder() {
        final String orderId = view.getOrderId();
        //TODO odpytać logikę aplikacyjną
        return new OrderApi(UUID.randomUUID().toString(), OrderState.CREATED, Set.of(), BigDecimal.ZERO);
    }

    private OrderApi addItem() {
        final String orderId = view.getOrderId();
        //TODO wywołać logikę aplikacyjną
        final ProductApi chocolate = new ProductApi(UUID.randomUUID().toString(),
                "Czekolada",
                BigDecimal.valueOf(4));
        final ProductApi cocaCola = new ProductApi(UUID.randomUUID().toString(),
                "Coca-cola",
                BigDecimal.valueOf(5));
        final String productId = view.getProduct(Set.of(chocolate, cocaCola));
        //TODO wywołać logikę aplikacyjną
        return new OrderApi(UUID.randomUUID().toString(), OrderState.CREATED, Set.of(), BigDecimal.ZERO);
    }

    private OrderApi removeItem() {
        //TODO wywołać logikę aplikacyjną
        final String orderId = view.getOrderId();
        //TODO wywołać logikę aplikacyjną
        final OrderApi order = new OrderApi(UUID.randomUUID().toString(), OrderState.CREATED, Set.of(), BigDecimal.ZERO);

        final String productId = view.getProduct(Set.of());
        return order;
    }


    private OrderApi exit() {
        view.sayGoodbye();
        return new OrderApi(UUID.randomUUID().toString(), OrderState.CREATED, Set.of(), BigDecimal.ZERO);
    }
}
