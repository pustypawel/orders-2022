package pl.edu.wszib.order.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.wszib.order.application.order.InMemoryOrderRepository;
import pl.edu.wszib.order.application.order.OrderFacade;
import pl.edu.wszib.order.application.order.OrderModule;
import pl.edu.wszib.order.application.order.OrderRepository;
import pl.edu.wszib.order.application.product.ProductFacade;
import pl.edu.wszib.order.infrastructure.persistance.orders.OrderDao;
import pl.edu.wszib.order.infrastructure.persistance.orders.SpringDataJpaOrderRepository;

@Configuration
public class OrderConfiguration {
    private final ProductFacade productFacade;
    private final OrderDao orderDao;

    public OrderConfiguration(final ProductFacade productFacade,
                              final OrderDao orderDao) {
        this.productFacade = productFacade;
        this.orderDao = orderDao;
    }

    @Bean
    public OrderFacade orderFacade() {
//        final OrderRepository orderRepository = new InMemoryOrderRepository();
        return new OrderModule(new SpringDataJpaOrderRepository(orderDao, productFacade), productFacade)
                .getFacade();
    }

    @Bean
    public ExampleCommandLineRunner exampleCommandLineRunner() {
        return new ExampleCommandLineRunner();
    }
}
