package pl.edu.wszib.order.api;

import lombok.Value;

import java.util.List;

@Value
public class PageApi {
    public enum Direction {
        ASC, DESC;
    }

    private final Integer page;
    private final Integer size;
    private final Sort sort;

    @Value
    public static class Sort {
        private final List<SortingOrder> sortingOrders;
    }

    @Value
    public static class SortingOrder {
        private final Direction direction;
        private final String property;
    }
}
