package pl.edu.wszib.order.application.order;

import pl.edu.wszib.order.application.product.ProductFacade;

public class OrderModuleInitialization {
    private final OrderFacade orderFacade;

    public OrderModuleInitialization(final ProductFacade productFacade) {
        final OrderRepository orderRepository = new InMemoryOrderRepository();
        this.orderFacade = new OrderModule(orderRepository, productFacade).getFacade();
    }

    public OrderFacade getProductFacade() {
        return orderFacade;
    }
}
