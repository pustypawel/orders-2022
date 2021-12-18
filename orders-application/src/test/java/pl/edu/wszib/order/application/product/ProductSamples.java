package pl.edu.wszib.order.application.product;

import java.math.BigDecimal;

public class ProductSamples {
    //TODO Dodać więcej produktów
    public static final Product CHOCOLATE = new Product(ProductId.create(), "Czekolada", BigDecimal.valueOf(4));
    public static final Product COCA_COLA_ZERO = new Product(ProductId.create(), "Coca-cola Zero", BigDecimal.valueOf(5));
}
