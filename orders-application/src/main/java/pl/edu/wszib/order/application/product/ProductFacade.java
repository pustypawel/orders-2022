package pl.edu.wszib.order.application.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.edu.wszib.order.api.product.ProductApi;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ProductFacade {
    private final ProductRepository productRepository;

    public ProductApi create(final ProductApi productApi) {
        final Product product = Product.create(productApi);
        return productRepository.save(product)
                .toApi();
    }

    public Optional<ProductApi> findById(final String id) {
        return findById(ProductId.of(id));
    }

    public Optional<ProductApi> findById(final ProductId id) {
        return productRepository.findById(id)
                .map(Product::toApi);
    }

    //TODO findAll
}
