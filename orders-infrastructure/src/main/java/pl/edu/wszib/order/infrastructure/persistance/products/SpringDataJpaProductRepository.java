package pl.edu.wszib.order.infrastructure.persistance.products;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pl.edu.wszib.order.api.PageApi;
import pl.edu.wszib.order.api.product.ProductApi;
import pl.edu.wszib.order.application.product.Product;
import pl.edu.wszib.order.application.product.ProductId;
import pl.edu.wszib.order.application.product.ProductRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SpringDataJpaProductRepository implements ProductRepository {
    private final ProductDao productDao;

    public SpringDataJpaProductRepository(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product save(final Product product) {
        final ProductEntity productEntity = ProductEntity.from(product.toApi());
        final ProductEntity savedProductEntity = productDao.saveAndFlush(productEntity);
        final ProductApi productApi = savedProductEntity.toApi();
        return Product.create(productApi);
    }

    @Override
    public Optional<Product> findById(final ProductId id) {
        return productDao.findById(id.asBasicType())
                .map(ProductEntity::toApi)
                .map(Product::create);
    }

    @Override
    public Set<Product> findAll(final PageApi pageApi) {
        final Sort sort = Sort.by(pageApi.getSort().getSortingOrders().stream()
                .map(sortingOrder -> new Sort.Order(Sort.Direction.fromString(sortingOrder.getDirection().name()), sortingOrder.getProperty()))
                .collect(Collectors.toList()));
        final PageRequest pageRequest = PageRequest.of(pageApi.getPage(), pageApi.getSize(), sort);
        return productDao.findAll(pageRequest).stream()
                .map(ProductEntity::toApi)
                .map(Product::create)
                .collect(Collectors.toSet());
    }
}
