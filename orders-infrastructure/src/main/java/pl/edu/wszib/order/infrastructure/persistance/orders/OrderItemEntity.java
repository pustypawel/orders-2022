package pl.edu.wszib.order.infrastructure.persistance.orders;

import pl.edu.wszib.order.api.order.OrderItemApi;
import pl.edu.wszib.order.application.product.ProductFacade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

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

    public OrderItemEntity(final String id,
                           final String productId,
                           final Integer quantity,
                           final BigDecimal amount) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
    }

    static Set<OrderItemEntity> fromAll(final Set<OrderItemApi> items) {
        return items.stream()
                .map(OrderItemEntity::from)
                .collect(Collectors.toSet());
    }

    static OrderItemEntity from(final OrderItemApi item) {
        return new OrderItemEntity(null, item.getProduct().getId(), item.getQuantity(), item.getAmount());
    }

    public static Set<OrderItemApi> toApi(final ProductFacade productFacade,
                                          final Set<OrderItemEntity> items) {
        return items.stream()
                .map(item -> item.toApi(productFacade))
                .collect(Collectors.toSet());
    }

    OrderItemApi toApi(final ProductFacade productFacade) {
        return new OrderItemApi(
                productFacade.findById(productId).orElseThrow(),
                quantity,
                amount
        );
    }

    void setOrder(final OrderEntity order) {
        this.order = order;
        this.id = order.getId() + "/" + productId;
    }
}
