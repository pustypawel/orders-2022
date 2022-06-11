package pl.edu.wszib.order.infrastructure.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.api.ErrorApi;
import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderApiResult;
import pl.edu.wszib.order.api.order.OrderError;
import pl.edu.wszib.order.application.order.OrderFacade;

import java.util.Collection;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
    private final OrderFacade orderFacade;

    public OrderRestController(final OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @GetMapping("{orderId}")
    public ResponseEntity<?> findOrderById(final @PathVariable String orderId) {
        final OrderApiResult result = orderFacade.findById(orderId);
        return handleResult(result);
    }

    @GetMapping
    public Collection<OrderApi> findAll(
            final @RequestParam("page") Integer page,
            final @RequestParam("size") Integer size,
            final @RequestParam("sort") String sort) {
        return orderFacade.findAll(page, size, sort);
    }

    @PostMapping
    public ResponseEntity<?> create() {
        final OrderApiResult result = orderFacade.create();
        return handleResult(result);
    }

    @PutMapping("/{orderId}/items/{productId}")
    public ResponseEntity<?> addProduct(final @PathVariable String orderId,
                                        final @PathVariable String productId,
                                        final @RequestParam(defaultValue = "1") Integer quantity) {
        final OrderApiResult result = orderFacade.addItem(orderId, productId, quantity);
        return handleResult(result);
    }

    @DeleteMapping("/{orderId}/items/{productId}")
    public ResponseEntity<?> removeProduct(final @PathVariable String orderId,
                                           final @PathVariable String productId) {
        final OrderApiResult result = orderFacade.removeItem(orderId, productId);
        return handleResult(result);
    }

    private ResponseEntity<?> handleResult(final OrderApiResult result) {
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getOrder());
        } else {
            return handleError(result.getError());
        }
    }

    private ResponseEntity<ErrorApi> handleError(final OrderError error) {
        switch (error) {
            case ORDER_NOT_FOUND:
                return new ResponseEntity<>(new ErrorApi(error.name(), "Order with given id does not exist."), HttpStatus.NOT_FOUND);
            case PRODUCT_NOT_FOUND:
                return new ResponseEntity<>(new ErrorApi(error.name(), "Product with given id does not exist."), HttpStatus.UNPROCESSABLE_ENTITY);
            default:
                throw new IllegalStateException("Unexpected value: " + error);
        }
    }
}
