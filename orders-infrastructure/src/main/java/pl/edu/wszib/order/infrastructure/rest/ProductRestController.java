package pl.edu.wszib.order.infrastructure.rest;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.order.api.ErrorApi;
import pl.edu.wszib.order.api.PageApi;
import pl.edu.wszib.order.api.product.ProductApi;
import pl.edu.wszib.order.api.product.ProductError;
import pl.edu.wszib.order.application.product.ProductFacade;
import pl.edu.wszib.order.infrastructure.utils.PageApiUtils;

import javax.validation.Valid;
import java.util.Set;

//TODO [ERROR_HANDLER] handling BAD_REQUEST with details about the errors
@RestController
@RequestMapping("/products")
public class ProductRestController {
    private final ProductFacade productFacade;

    public ProductRestController(final ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    public Set<ProductApi> searchProducts(final Pageable pageable) {
        final PageApi pageApi = PageApiUtils.fromPageable(pageable);
        return productFacade.search(pageApi);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(final @PathVariable String productId,
                                            final @RequestHeader String host) {
        System.out.println("Host: " + host);
        return productFacade.findById(productId)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(new ErrorApi(ProductError.PRODUCT_NOT_FOUND.name(), "Product with given id does not exist"), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ProductApi createProduct(final @RequestBody @Valid ProductApi productApi) {
        return productFacade.create(productApi);
    }
}
