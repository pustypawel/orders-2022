package pl.edu.wszib.order.application.product;

import pl.edu.wszib.order.api.PageApi;

import java.util.Optional;
import java.util.Set;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(ProductId id);

    Set<Product> findAll(PageApi pageApi);
}