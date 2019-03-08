package pt.hlbk.orders.events;

import java.util.StringJoiner;
import java.util.UUID;

public class OrderEventPersistRequest implements OrderEvent {

    private final String eventId;
    private final OrderEvent orderEvent;

    public OrderEventPersistRequest(final String eventId,
                                    final OrderEvent orderEvent) {
        this.eventId = eventId;
        this.orderEvent = orderEvent;
    }

    public static OrderEventPersistRequest of(final OrderEvent orderEvent) {
        return new OrderEventPersistRequest(UUID.randomUUID().toString(), orderEvent);
    }

    public OrderEvent getOrderEvent() {
        return orderEvent;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderEventPersistRequest.class.getSimpleName() + "[", "]")
                .add("eventId='" + eventId + "'")
                .add("orderEvent=" + orderEvent)
                .toString();
    }
}
