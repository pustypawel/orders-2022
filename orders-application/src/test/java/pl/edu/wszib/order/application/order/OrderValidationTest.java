package pl.edu.wszib.order.application.order;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderItemApi;
import pl.edu.wszib.order.api.order.OrderState;
import pl.edu.wszib.order.application.product.ProductSamples;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderValidationTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void validation_should_work() {
        OrderApi orderApi = new OrderApi(UUID.randomUUID().toString(),
                OrderState.CREATED,
                Set.of(new OrderItemApi(ProductSamples.CHOCOLATE, 1, BigDecimal.valueOf(4))),
                BigDecimal.valueOf(4));
        final Set<ConstraintViolation<OrderApi>> validationResult = validator.validate(orderApi);
        validationResult.forEach(validation -> System.out.println("Pole " + validation.getPropertyPath() + " " + validation.getMessage()));
        assertTrue(validationResult.isEmpty());
    }

}
