package pl.edu.wszib.order.application.product;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Objects;

@Value
public class Product {
    private final ProductId id;
    private final String name;
    //można docelowo przejść na:
    //https://github.com/JavaMoney/jsr354-api
    private final BigDecimal price;

    public boolean hasId(final String productId) {
        return id.hasId(productId);
    }
}
