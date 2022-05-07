package pl.edu.wszib.order.infrastructure.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.wszib.order.application.product.InMemoryProductRepository;
import pl.edu.wszib.order.application.product.ProductFacade;
import pl.edu.wszib.order.application.product.ProductModule;
import pl.edu.wszib.order.application.product.ProductRepository;

@Configuration
@EnableConfigurationProperties({ProductProperties.class, NativeProductProperties.class})
public class ProductConfiguration {
    //TODO [PRODUCT] produkty definiowane w pliku konfiguracyjnym
    private final ProductProperties productProperties;

    private final NativeProductProperties nativeProductProperties;

    @Value("${my.prop}")
    private String myProp;

    public ProductConfiguration(final ProductProperties productProperties,
                                final NativeProductProperties nativeProductProperties) {
        this.productProperties = productProperties;
        this.nativeProductProperties = nativeProductProperties;
    }

    @Bean
    public ProductFacade productFacade() {
        final ProductRepository productRepository = new InMemoryProductRepository();
        return new ProductModule(productRepository)
                .getFacade();
    }

    @Bean
    public InitializingBean initProducts() {
        return () -> {
            // TODO initialize predefined products
        };
    }
}
