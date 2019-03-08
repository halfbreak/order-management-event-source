package pt.hlbk.orders.events;

import java.util.StringJoiner;
import java.util.UUID;

public class OrderTrackingStarted implements OrderEvent {
    private String eventId;
    private String orderId;

    public OrderTrackingStarted(String orderId) {
        this.eventId = UUID.randomUUID().toString();
        this.orderId = orderId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderTrackingStarted.class.getSimpleName() + "[", "]")
                .add("eventId='" + eventId + "'")
                .add("orderId='" + orderId + "'")
                .toString();
    }
}
