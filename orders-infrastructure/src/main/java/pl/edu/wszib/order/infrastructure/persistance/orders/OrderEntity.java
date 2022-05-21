package pl.edu.wszib.order.infrastructure.persistance.orders;

import pl.edu.wszib.order.api.order.OrderState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "orders",
        indexes = @Index(name = "IX_ORDERS_ID", columnList = "id", unique = true)
)
public class OrderEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private OrderState state;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "order")
    private Set<OrderItemEntity> items;

    protected OrderEntity() {
        // for Hibernate
    }

    public OrderEntity(String id,
                       OrderState state,
                       BigDecimal amount,
                       Set<OrderItemEntity> items) {
        this.id = id;
        this.state = state;
        this.amount = amount;
        this.items = items;
    }
}
