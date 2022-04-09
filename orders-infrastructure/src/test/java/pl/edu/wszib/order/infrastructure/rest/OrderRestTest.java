package pl.edu.wszib.order.infrastructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

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
}
