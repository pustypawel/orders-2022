package pl.edu.wszib.order.infrastructure.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.wszib.order.api.product.ProductApi;
import pl.edu.wszib.order.application.product.InMemoryProductRepository;
import pl.edu.wszib.order.application.product.ProductFacade;
import pl.edu.wszib.order.application.product.ProductModule;
import pl.edu.wszib.order.application.product.ProductRepository;
import pl.edu.wszib.order.infrastructure.properties.ProductProperties;

@Configuration
@EnableConfigurationProperties({ProductProperties.class})
public class ProductConfiguration {
    private final ProductProperties productProperties;


    public ProductConfiguration(final ProductProperties productProperties) {
        this.productProperties = productProperties;
    }

    @Bean
    public ProductFacade productFacade() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        return new ProductModule(productRepository)
                .getFacade();
    }

    @Bean
    public InitializingBean initProducts(final ProductFacade productFacade) {
        return () -> {
            productProperties.getInitials()
                    .forEach(productFacade::create);
//            for (ProductApi initialProduct : productProperties.getInitials()) {
//                productFacade.create(initialProduct);
//            }
        };
    }
}
