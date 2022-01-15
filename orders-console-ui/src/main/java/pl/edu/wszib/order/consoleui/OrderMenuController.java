package pl.edu.wszib.order.consoleui;

import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderApiResult;
import pl.edu.wszib.order.api.order.OrderState;
import pl.edu.wszib.order.api.product.ProductApi;
import pl.edu.wszib.order.application.order.OrderFacade;
import pl.edu.wszib.order.application.product.ProductFacade;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

class OrderMenuController {
    private final OrderMenuView view;
    private final OrderFacade orderFacade;
    private final ProductFacade productFacade;

    public OrderMenuController(final OrderMenuView view,
                               final OrderFacade orderFacade,
                               final ProductFacade productFacade) {
        this.view = view;
        this.orderFacade = orderFacade;
        this.productFacade = productFacade;
    }

    OrderApiResult handle(final OrderMenuOption option) {
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

    private OrderApiResult createOrder() {
        return orderFacade.create();
    }

    private OrderApiResult getOrder() {
        final String orderId = view.getOrderId();
        return orderFacade.findById(orderId);
    }

    private OrderApiResult addItem() {
        final String orderId = view.getOrderId();
        final Set<ProductApi> products = productFacade.findAll();
        final String productId = view.getProduct(products);
        return orderFacade.addItem(orderId, productId, 1);  //TODO pytanie o quantity
    }

    private OrderApiResult removeItem() {
        final String orderId = view.getOrderId();
        final OrderApiResult result = orderFacade.findById(orderId);
        if (result.isSuccess()) {
            final OrderApi order = result.getOrder();
            final String productId = view.getProduct(order.getProducts());
            return orderFacade.removeItem(orderId, productId);
        }
        return result;
    }


    private OrderApiResult exit() {
        view.sayGoodbye();
        return null;
    }
}
