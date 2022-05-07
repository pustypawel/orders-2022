package pl.edu.wszib.order.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;
import pl.edu.wszib.order.api.product.ProductApi;

import javax.validation.Valid;
import java.util.Set;

@ConstructorBinding
@ConfigurationProperties(prefix = "orders.products.native")
@Validated
public class NativeProductProperties {
    private final Set<@Valid ProductApi> initials;

    public NativeProductProperties(Set<@Valid ProductApi> initials) {
        this.initials = initials;
    }

    public Set<ProductApi> getInitials() {
        return initials;
    }
}
