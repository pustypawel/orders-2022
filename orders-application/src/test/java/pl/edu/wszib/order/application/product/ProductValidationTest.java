package pl.edu.wszib.order.application.product;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void validation_should_work() {
        final Product product = new Product(
                ProductId.create(),
                "My correct product",
                BigDecimal.valueOf(4));
        final Set<ConstraintViolation<Product>> validationResult = validator.validate(product);
        validationResult.forEach(validation -> System.out.println("Pole " + validation.getPropertyPath() + " " + validation.getMessage()));
        assertTrue(validationResult.isEmpty());
    }
}
