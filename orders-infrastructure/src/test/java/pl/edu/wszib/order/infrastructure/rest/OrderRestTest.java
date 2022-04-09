package pl.edu.wszib.order.infrastructure.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.product.ProductApi;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class OrderRestTest {
    @Autowired
    private TestRestTemplate restTemplate;

    //TODO [TASK] impl orders tests:
    // should_be_able_to_create_order
    // should_be_able_to_add_item_to_order
    // should_be_able_to_remove_item_from_order
    // should_be_able_to_find_order
    // should_be_able_to_meet_acceptance_criteria

    @BeforeEach
    public void setup() {
        initProduct(ProductSamples.CHOCOLATE);
        initProduct(ProductSamples.COCA_COLA_ZERO);
    }

    private void initProduct(final ProductApi product) {
        final ResponseEntity<ProductApi> response = restTemplate.postForEntity("/products", product, ProductApi.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void should_be_able_to_create_order() {
        //given:

        //when:
        final ResponseEntity<OrderApi> result = restTemplate.postForEntity("/orders", null, OrderApi.class);

        //then:
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNotNull(result.getBody());
    }

    @Test
    public void should_be_able_to_add_item_to_order() {
        //given:
        final String existingOrderId = restTemplate.postForEntity("/orders", null, OrderApi.class)
                .getBody()
                .getId();
        final String existingProductId = ProductSamples.CHOCOLATE.getId();

        //when:
        final ResponseEntity<OrderApi> result = restTemplate.exchange("/orders/{orderId}/items/{productId}",
                HttpMethod.PUT,
                null,
                OrderApi.class,
                existingOrderId,
                existingProductId
        );

        //then:
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().getItems().size() > 0);
    }

    @Test
    public void should_be_able_to_remove_item_from_order() {
        //given:
        final String existingOrderId = restTemplate.postForEntity("/orders", null, OrderApi.class)
                .getBody()
                .getId();
        final String existingProductId = ProductSamples.CHOCOLATE.getId();
        restTemplate.put("/orders/{orderId}/items/{productId}", null, existingOrderId, existingProductId);

        // when:
        final ResponseEntity<OrderApi> result = restTemplate.exchange("/orders/{orderId}/items/{productId}",
                HttpMethod.DELETE,
                null,
                OrderApi.class,
                existingOrderId,
                existingProductId
        );

        //then:
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNotNull(result.getBody());
        assertEquals(0, result.getBody().getItems().size());
    }

    @Test
    public void should_be_able_to_find_order() {
        //given:
        final String existingOrderId = restTemplate.postForEntity("/orders", null, OrderApi.class)
                .getBody()
                .getId();

        //when:
        final ResponseEntity<OrderApi> result = restTemplate.getForEntity("/orders/{orderId}", OrderApi.class, existingOrderId);

        //then:
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNotNull(result.getBody());
    }

    @Test
    public void should_be_able_to_meet_acceptance_criteria() {
        //given:
        final String chocolateId = ProductSamples.CHOCOLATE.getId();
        final String cocaColaZeroId = ProductSamples.COCA_COLA_ZERO.getId();

        //when:
        final String orderId = restTemplate.postForEntity("/orders", null, OrderApi.class)
                .getBody()
                .getId();
        restTemplate.exchange("/orders/{orderId}/items/{productId}",
                HttpMethod.PUT,
                null,
                OrderApi.class,
                orderId,
                chocolateId
        );
        restTemplate.exchange("/orders/{orderId}/items/{productId}",
                HttpMethod.PUT,
                null,
                OrderApi.class,
                orderId,
                cocaColaZeroId
        );
        restTemplate.exchange("/orders/{orderId}/items/{productId}",
                HttpMethod.DELETE,
                null,
                OrderApi.class,
                orderId,
                chocolateId
        );
        final ResponseEntity<OrderApi> result = restTemplate.getForEntity("/orders/{orderId}", OrderApi.class, orderId);

        //then:
        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().getItems().size() > 0);
    }
}
