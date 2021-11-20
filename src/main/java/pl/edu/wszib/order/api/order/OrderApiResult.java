package pl.edu.wszib.order.api.order;

import lombok.Value;

@Value
public class OrderApiResult {
    private final OrderApi order;
    private final OrderError error;

    public static OrderApiResult success(final OrderApi order) {
        return new OrderApiResult(order, null);
    }

    public static OrderApiResult failure(final OrderError error) {
        return new OrderApiResult(null, error);
    }

    public boolean isSuccess() {
        return order != null;
    }

    public boolean isFailure() {
        return !isSuccess();
    }
}
