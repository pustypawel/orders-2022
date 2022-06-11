package pl.edu.wszib.order.infrastructure.persistance.products;

import pl.edu.wszib.order.application.product.Product;
import pl.edu.wszib.order.application.product.ProductId;
import pl.edu.wszib.order.application.product.ProductRepository;

import java.util.Optional;
import java.util.Set;

// TODO [TASK] impl persistence layer for products
public class SpringDataJpaProductRepository implements ProductRepository {
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return Optional.empty();
    }

    @Override
    public Set<Product> findAll() {
        return null;
    }
}
