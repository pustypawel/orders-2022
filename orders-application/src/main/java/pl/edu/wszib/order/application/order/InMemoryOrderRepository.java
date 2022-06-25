package pl.edu.wszib.order.application.order;

import pl.edu.wszib.order.api.PageApi;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryOrderRepository implements OrderRepository {

    private final Map<OrderId, Order> orders = new HashMap<>();

    @Override
    public Order save(final Order order) {
        orders.put(order.getId(), order);
        return order;
    }

//    Map<String, Comparator<?>> comparatorMap = Map.of(
//            "amount", new Comparator<BigDecimal>() {
//            },
//                    ....
//    )

    @Override
    public List<Order> findAll(final PageApi pageApi) {
        // sort by amount
        PageApi.Sort sort = pageApi.getSort();
        final List<PageApi.SortingOrder> sortingOrders = sort.getSortingOrders();
        return orders.values().stream()
                .sorted((o1, o2) -> {
                    for (PageApi.SortingOrder sortingOrder : sortingOrders) {
                        final PageApi.Direction direction = sortingOrder.getDirection();
                        if (sortingOrder.getProperty().equals("amount")) {
                            int result = o1.toApi().getAmount().compareTo(o2.toApi().getAmount());
                            if (result != 0) {
                                return direction == PageApi.Direction.ASC ? result : Math.negateExact(result);
                            }
                            continue;
                        }
                        throw new UnsupportedOperationException("Can't do sorting by " + sortingOrders);
                    }
                    return 0;
                }).
                skip((long) pageApi.getSize() * pageApi.getPage()).
                limit(pageApi.getSize()).
                collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findById(final OrderId id) {
        return Optional.ofNullable(orders.get(id));
    }
}
