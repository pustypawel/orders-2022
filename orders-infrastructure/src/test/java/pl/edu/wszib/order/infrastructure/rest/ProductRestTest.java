package pl.edu.wszib.order.infrastructure.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pl.edu.wszib.order.api.product.ProductApi;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String existingProductId;

    @BeforeEach
    public void setup() {
        final ProductApi product = ProductSamples.CHOCOLATE;
        final ResponseEntity<ProductApi> response = restTemplate.postForEntity("/products", product, ProductApi.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        existingProductId = product.getId();
    }

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
        //given:
        final String productId = existingProductId;

        //when:
        final ResponseEntity<ProductApi> response = restTemplate.getForEntity("/products/{productId}", ProductApi.class, productId);

        //then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(productId, response.getBody().getId());
    }

    @Test
    public void should_be_able_to_find_all_products() {
        //given:
        final ProductApi product = ProductSamples.COCA_COLA_ZERO;
        restTemplate.postForEntity("/products", product, ProductApi.class);

        //when:
        final ResponseEntity<ProductApi[]> response = restTemplate.getForEntity("/products", ProductApi[].class);

        //then
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    public void should_not_be_able_to_create_invalid_product() {
        //given:
        final ProductApi invalidProduct = ProductSamples.INVALID;

        //when:
        final ResponseEntity<ProductApi> response = restTemplate.postForEntity("/products", invalidProduct, ProductApi.class);

        //then
        assertTrue(response.getStatusCode().is4xxClientError());
    }
}
