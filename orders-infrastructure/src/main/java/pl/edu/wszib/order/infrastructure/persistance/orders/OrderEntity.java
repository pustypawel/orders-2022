package pl.edu.wszib.order.infrastructure.persistance.orders;

import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderState;
import pl.edu.wszib.order.application.product.ProductFacade;

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
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "order")
    private Set<OrderItemEntity> items;

    protected OrderEntity() {
        // for Hibernate
    }

    OrderEntity(final String id,
                final OrderState state,
                final BigDecimal amount,
                final Set<OrderItemEntity> items) {
        this.id = id;
        this.state = state;
        this.amount = amount;
        this.items = items;
        this.items.forEach(item -> item.setOrder(this));
    }

    public static OrderEntity from(final OrderApi orderApi) {
        return new OrderEntity(
                orderApi.getId(),
                orderApi.getState(),
                orderApi.getAmount(),
                OrderItemEntity.fromAll(orderApi.getItems())
        );
    }

    public OrderApi toApi(final ProductFacade productFacade) {
        return new OrderApi(id, state, OrderItemEntity.toApi(productFacade, items), amount);
    }

    public String getId() {
        return id;
    }
}
