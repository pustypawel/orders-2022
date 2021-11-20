package pl.edu.wszib.order.application.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.wszib.order.api.order.OrderApi;
import pl.edu.wszib.order.api.order.OrderItemAddApi;
import pl.edu.wszib.order.application.product.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrderFacadeTest {
    //T2: można dodać pozycję do zamówienia
    //T3: można usunąć pozycję
    //T4: można zwiększyć ilość dla pozycji
    //T5: można zmniejszyć ilość dla pozycji
    //T6: dodanie pozycji przedmiotu, który znajduje się już w zamówieniu powinno skutkować zwiększeniem jego ilości
    //T7: można ukończyć zamówienie
    //T8: nie można modyfikować ukończonego zamówienia
    //T9: wartość zamówienia powinna być sumą wartości pozycji, wartość pozycji to cena jednostkowa * ilość

    private OrderFacade orderFacade;

    @BeforeEach
    public void setup() {
        final OrderRepository orderRepository = new InMemoryOrderRepository();
        //TODO Refactor
        final ProductFacade productFacade = new ProductFacade(new InMemoryProductRepository());
        new ProductRepositoryInitialization().init(productFacade);
        orderFacade = new OrderFacade(orderRepository, productFacade);
    }

    @Test
    public void should_be_able_to_create_order() {
        //given:

        //when:
        final OrderApi createdOrder = orderFacade.create();

        //then:
        final Optional<OrderApi> foundOrder = orderFacade.findById(createdOrder.getId());
        assertTrue(foundOrder.isPresent());
        System.out.println("Order has been created! order = " + createdOrder);
    }

    @Test
    public void should_be_able_to_add_item_to_order() {
        //given:
        final String orderId = orderFacade.create().getId();

        final OrderItemAddApi itemToAdd = new OrderItemAddApi(ProductSamples.CHOCOLATE.getId().asBasicType(), 1);

        //when:
        orderFacade.addItem(orderId, itemToAdd);

        //then:
        Optional<OrderApi> foundOrder = orderFacade.findById(orderId);
        assertTrue(foundOrder.isPresent());
        boolean orderContainsProductWeWantedToAdd = foundOrder.get().getItems().stream().anyMatch(orderItemApi -> orderItemApi.getProductId().equals(itemToAdd.getProductId()));
        assertTrue(orderContainsProductWeWantedToAdd);

        System.out.println("FoundOrder: " + foundOrder);
    }
}
