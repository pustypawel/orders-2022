package pl.edu.wszib.order.infrastructure.rest;

import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.api.product.ProductApi;
import pl.edu.wszib.order.application.product.ProductFacade;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

//TODO [ERROR_HANDLER] handling BAD_REQUEST with details about the errors
@RestController
@RequestMapping("/products")
public class ProductRestController {
    private final ProductFacade productFacade;

    public ProductRestController(final ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    //TODO [PRODUCT] paginacja, zamiast required użyć default
    @GetMapping
    public Set<ProductApi> getAllProducts(final @RequestParam(required = false) Integer limit,
                                          final @RequestParam(required = false) Integer size) {
        return productFacade.findAll();
    }

    //TODO [PRODUCT] optional nie zwraca 404
    @GetMapping("/{productId}")
    public Optional<ProductApi> getProductById(final @PathVariable String productId,
                                               final @RequestHeader String host) {
        System.out.println("Host: " + host);
        return productFacade.findById(productId);
    }

    @PostMapping
    public ProductApi createProduct(final @RequestBody @Valid ProductApi productApi) {
        return productFacade.create(productApi);
    }
}
