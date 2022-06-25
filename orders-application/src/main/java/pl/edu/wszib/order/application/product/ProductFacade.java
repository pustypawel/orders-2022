package pl.edu.wszib.order.application.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.edu.wszib.order.api.PageApi;
import pl.edu.wszib.order.api.product.ProductApi;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<ProductApi> search(final PageApi pageApi) {
        return productRepository.findAll(pageApi).stream()
                .map(Product::toApi)
                .collect(Collectors.toSet());
    }
}

/*
/products/{productId}
/products
GET - do pobierania danych
/products
POST - do tworzenia zasobów
/order/{productId}
PUT - do tworzenia i nadpisania zasobów
/order/{productId}
DELETE - do usuwania
/order/{productId}
PATCH - do aktualizacji/częściowej aktualizacji

HEADER
Header1: My header
Header2: My header2
Content-type: application/json

{
"test": "my body"
}
 */