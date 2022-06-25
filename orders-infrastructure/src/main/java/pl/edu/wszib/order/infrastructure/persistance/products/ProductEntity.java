package pl.edu.wszib.order.infrastructure.persistance.products;

import pl.edu.wszib.order.api.product.ProductApi;
import pl.edu.wszib.order.application.product.ProductId;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products"
//        indexes = @Index(name = "IX_ORDERS_ID", columnList = "id", unique = true)
)
public class ProductEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    protected ProductEntity() {
        // for Hibernate
    }

    private ProductEntity(final String id,
                          final String name,
                          final BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static ProductEntity from(final ProductApi productApi) {
        return new ProductEntity(
                productApi.getId(),
                productApi.getName(),
                productApi.getPrice()
        );
    }

    public ProductApi toApi() {
        return new ProductApi(id, name, price);
    }
}
