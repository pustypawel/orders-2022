package pl.edu.wszib.order.infrastructure.persistance.orders;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items",
        uniqueConstraints = @UniqueConstraint(name = "UQ_ORDER_ITEMS_ORDER_ID_PRODUCT_ID",
                columnNames = {"order_id", "productId"})
)
public class OrderItemEntity {
    @Id
    private String id;

    @ManyToOne(optional = false)
    private OrderEntity order;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal amount;

    protected OrderItemEntity() {
        // for Hibernate
    }

    public OrderItemEntity(String id,
                           String productId,
                           Integer quantity,
                           BigDecimal amount) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
    }

    void setOrder(OrderEntity order) {
        this.order = order;
    }
}
