package pl.edu.wszib.order.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.wszib.order.application.order.InMemoryOrderRepository;
import pl.edu.wszib.order.application.order.OrderFacade;
import pl.edu.wszib.order.application.order.OrderModule;
import pl.edu.wszib.order.application.order.OrderRepository;
import pl.edu.wszib.order.application.product.ProductFacade;

@Configuration
public class OrderConfiguration {
    private final ProductFacade productFacade;
    
    public OrderConfiguration(final ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @Bean
    public OrderFacade orderFacade() {
        final OrderRepository orderRepository = new InMemoryOrderRepository();
        return new OrderModule(orderRepository, productFacade)
                .getFacade();
    }

    @Bean
    public ExampleCommandLineRunner exampleCommandLineRunner() {
        return new ExampleCommandLineRunner();
    }
}
