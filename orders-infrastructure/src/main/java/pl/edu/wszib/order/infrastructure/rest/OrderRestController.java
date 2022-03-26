package pl.edu.wszib.order.infrastructure.rest;

import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderApiResult;
import pl.edu.wszib.order.application.order.OrderFacade;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
    //TODO [TASK] Impl:
    // 1. RestController impl
    // 2. rest-api-orders.http - example calls
    private final OrderFacade orderFacade;

    public OrderRestController(final OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @GetMapping("{orderId}")
    public Optional<OrderApi> findOrderById(@PathVariable String orderId) {
        final OrderApiResult result = orderFacade.findById(orderId);
        if (result.isSuccess()) {
            return Optional.of(result.getOrder());
        } else {    //TODO [ERROR_HANDLING]
            return Optional.empty();
        }
    }

    @GetMapping
    public Collection<OrderApi> findAllOrder() { //TODO paginacja, sortowanie
        return orderFacade.findAll();
    }

    @PostMapping
    public OrderApi create() {
        return orderFacade.create() //TODO [ERROR_HANDLING]
                .getOrder();
    }

    @PutMapping("/{orderId}/items/{productId}")
    public Optional<OrderApi> addProduct(@PathVariable String orderId,
                                         @PathVariable String productId,
                                         @RequestParam(defaultValue = "1") Integer quantity) {
        final OrderApiResult result = orderFacade.addItem(orderId, productId, quantity);
        if (result.isSuccess()) {
            return Optional.of(result.getOrder());
        } else {    //TODO [ERROR_HANDLING]
            return Optional.empty();
        }
    }

    @DeleteMapping("/{orderId}/items/{productId}")
    public Optional<OrderApi> removeProduct(@PathVariable String orderId,
                                            @PathVariable String productId) {
        final OrderApiResult result = orderFacade.removeItem(orderId, productId);
        if (result.isSuccess()) {
            return Optional.of(result.getOrder());
        } else {    //TODO [ERROR_HANDLING]
            return Optional.empty();
        }
    }

}
