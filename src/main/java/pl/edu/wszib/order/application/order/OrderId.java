package pl.edu.wszib.order.application.order;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderId {
    private final String id;

    public static OrderId create() {
        return new OrderId(UUID.randomUUID().toString());
    }

    public static OrderId of(final String id) {
        return new OrderId(id);
    }

    public String asBasicType() {
        return id;
    }

}
