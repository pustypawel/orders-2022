package pl.edu.wszib.order.application.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        orderFacade = new OrderFacade(orderRepository);
    }

    @Test
    public void should_be_able_to_create_order() {
        //given:

        //when:
        final Order createdOrder = orderFacade.create();

        //then:
        final Optional<Order> foundOrder = orderFacade.findById(createdOrder.getId());
        assertTrue(foundOrder.isPresent());
        System.out.println("Order has been created! order = " + createdOrder);
    }

    @Test
    public void should_be_able_to_add_item_to_order() {
        //given:
        final OrderId orderId = orderFacade.create().getId();

        //when:
        orderFacade.addItem(orderId, OrderItem.create());

        //then:
        Optional<Order> foundOrder = orderFacade.findById(orderId);
        System.out.println("FoundOrder: " + foundOrder);
    }
}
