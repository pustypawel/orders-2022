package pl.edu.wszib.order.application.product;

import lombok.Value;
import pl.edu.wszib.order.api.product.ProductApi;

import java.math.BigDecimal;

@Value
public class Product {
    private final ProductId id;

    private final String name;

    //można docelowo przejść na:
    //https://github.com/JavaMoney/jsr354-api
    private final BigDecimal price;

    public static Product create(final ProductApi productApi) {
        return new Product(ProductId.of(productApi.getId()), productApi.getName(), productApi.getPrice());
    }

    public boolean hasId(final String productId) {
        return id.hasId(productId);
    }

    public ProductApi toApi() {
        return new ProductApi(id.asBasicType(), name, price);
    }
}
