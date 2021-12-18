package pl.edu.wszib.order.application.product;

import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(ProductId id);
}