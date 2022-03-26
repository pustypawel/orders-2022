package pl.edu.wszib.order.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.wszib.order.application.product.InMemoryProductRepository;
import pl.edu.wszib.order.application.product.ProductFacade;
import pl.edu.wszib.order.application.product.ProductModule;
import pl.edu.wszib.order.application.product.ProductRepository;

@Configuration
public class ProductConfiguration {
    //TODO [PRODUCT] produkty definiowane w pliku konfiguracyjnym
    @Bean
    public ProductFacade productFacade() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        return new ProductModule(productRepository)
                .getFacade();
    }
}
