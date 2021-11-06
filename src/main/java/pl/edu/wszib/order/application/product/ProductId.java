package pl.edu.wszib.order.application.product;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@ToString
public class ProductId {
    private final String id;

    public static ProductId create() {
        return new ProductId(UUID.randomUUID().toString());
    }
}
