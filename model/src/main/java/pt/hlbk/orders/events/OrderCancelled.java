package pt.hlbk.orders.events;

public class OrderCancelled implements OrderEvent {

    private final String eventId;
    private final String orderId;

    public OrderCancelled(final String eventId,
                          final String orderId) {
        this.eventId = eventId;
        this.orderId = orderId;
    }
}
