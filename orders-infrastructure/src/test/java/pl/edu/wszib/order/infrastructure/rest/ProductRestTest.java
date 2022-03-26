package pl.edu.wszib.order.infrastructure.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import pl.edu.wszib.order.api.product.ProductApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_be_able_to_create_product() {
        //given:
        final ProductApi product = ProductSamples.CHOCOLATE;

        //when:
        final ResponseEntity<ProductApi> response = restTemplate.postForEntity("/products", product, ProductApi.class);

        //then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(product, response.getBody());
    }

    @Test
    public void should_be_able_to_find_product_by_id() {
        // TODO [TASK] impl
    }

    @Test
    public void should_be_able_to_find_all_products() {
        // TODO [TASK] impl
    }
}
