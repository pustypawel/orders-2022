package pl.edu.wszib.order.api.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Value
public class ProductApi {
    @NotNull
    private final String id;

    @NotNull
    @Length(min = 2, max = 50)
    private final String name;

    //można docelowo przejść na:
    //https://github.com/JavaMoney/jsr354-api
    @NotNull
    @Positive
    private final BigDecimal price;
}
