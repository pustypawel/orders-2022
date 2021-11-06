package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@ToString
public class OrderId {
    private final String id;

    public static OrderId create() {
        return new OrderId(UUID.randomUUID().toString());
    }
}
