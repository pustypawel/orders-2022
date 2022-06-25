package pl.edu.wszib.order.application.product;

import pl.edu.wszib.order.api.PageApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<ProductId, Product> products = new HashMap<>();

    @Override
    public Product save(final Product product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(final ProductId id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Set<Product> findAll(PageApi pageApi) {
        return Set.copyOf(products.values());
    }

}
