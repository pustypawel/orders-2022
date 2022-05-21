package pl.edu.wszib.order.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
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

    public OrderConfiguration(final ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @Bean
    public OrderFacade orderFacade(final OrderRepository orderRepository) {
        return new OrderModule(orderRepository, productFacade)
                .getFacade();
    }

    @Bean
    @Profile("inmemory")
    public OrderRepository inMemoryOrderRepository() {
        return new InMemoryOrderRepository();
    }

    @Bean
    @Profile("!inmemory")
    public OrderRepository databaseOrderRepository(final OrderDao orderDao) {
        return new SpringDataJpaOrderRepository(orderDao, productFacade);
    }

    @Bean
    public ExampleCommandLineRunner exampleCommandLineRunner() {
        return new ExampleCommandLineRunner();
    }
}
