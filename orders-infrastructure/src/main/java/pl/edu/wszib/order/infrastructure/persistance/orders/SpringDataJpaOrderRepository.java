package pl.edu.wszib.order.infrastructure.persistance.orders;

import org.springframework.transaction.annotation.Transactional;
import pl.edu.wszib.order.application.order.Order;
import pl.edu.wszib.order.application.order.OrderId;
import pl.edu.wszib.order.application.order.OrderRepository;
import pl.edu.wszib.order.application.product.ProductFacade;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
public class SpringDataJpaOrderRepository implements OrderRepository {
    private final OrderDao orderDao;
    private final ProductFacade productFacade;

    public SpringDataJpaOrderRepository(final OrderDao orderDao,
                                        final ProductFacade productFacade) {
        this.orderDao = orderDao;
        this.productFacade = productFacade;
    }

//    @Transactional
//    @Override
//    public void execute(final Runnable runnable) {
//        runnable.run();
//    }
//
//    @Transactional
//    @Override
//    public <T> T execute(final Supplier<T> runnable) {
//        return runnable.get();
//    }

    @Override
    public Optional<Order> findById(final OrderId id) {
        return orderDao.findById(id.asBasicType())
                .map(orderEntity -> orderEntity.toApi(productFacade))
                .map(Order::fromApi);
    }

    @Transactional
    @Override
    public Order save(final Order order) {
        final OrderEntity orderEntity = OrderEntity.from(order.toApi());
        final OrderEntity savedOrderEntity = orderDao.saveAndFlush(orderEntity);
        return Order.fromApi(savedOrderEntity.toApi(productFacade));
    }

    @Override
    public Collection<Order> findAll() {
        return orderDao.findAll().stream()
                .map(orderEntity -> orderEntity.toApi(productFacade))
                .map(Order::fromApi)
                .collect(Collectors.toSet());
    }
}
