package pt.hlbk.orders.events;

import pt.hlbk.orders.Order;

import java.util.StringJoiner;
import java.util.UUID;

public class OrderEventPersistResponse implements OrderEvent {

    private final String requestId;
    private final Order order;

    public OrderEventPersistResponse(final String requestId, final Order order) {
        this.requestId = requestId;
        this.order = order;
    }

    public static OrderEventPersistResponse of(final Order order) {
        return new OrderEventPersistResponse(UUID.randomUUID().toString(), order);
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderEventPersistResponse.class.getSimpleName() + "[", "]")
                .add("requestId='" + requestId + "'")
                .add("order=" + order)
                .toString();
    }
}
