package pl.edu.wszib.order.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.wszib.order.application.product.InMemoryProductRepository;
import pl.edu.wszib.order.application.product.ProductFacade;
import pl.edu.wszib.order.application.product.ProductModule;
import pl.edu.wszib.order.application.product.ProductRepository;

@Configuration
@EnableConfigurationProperties(ProductProperties.class)
public class ProductConfiguration {
    //TODO [PRODUCT] produkty definiowane w pliku konfiguracyjnym
    private final ProductProperties productProperties;

    @Value("${my.prop}")
    private String myProp;

    public ProductConfiguration(final ProductProperties productProperties) {
        this.productProperties = productProperties;
    }

    @Bean
    public ProductFacade productFacade() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        return new ProductModule(productRepository)
                .getFacade();
    }
}
