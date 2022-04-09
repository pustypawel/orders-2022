package pl.edu.wszib.order.infrastructure.rest;

import pl.edu.wszib.order.api.product.ProductApi;
import pl.edu.wszib.order.application.product.ProductId;

import java.math.BigDecimal;

public class ProductSamples {
    public static final ProductApi CHOCOLATE = new ProductApi(ProductId.create().asBasicType(), "Czekolada", BigDecimal.valueOf(4));
    public static final ProductApi COCA_COLA_ZERO = new ProductApi(ProductId.create().asBasicType(), "Coca-cola Zero", BigDecimal.valueOf(5));

    public static final ProductApi INVALID = new ProductApi(null, null, null);
}
