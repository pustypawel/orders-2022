package pl.edu.wszib.order.infrastructure.persistance.orders;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderEntity {
    @Id
    private String id;

    protected OrderEntity() {
        // for Hibernate
    }

    public OrderEntity(final String id) {
        this.id = id;
    }
}
