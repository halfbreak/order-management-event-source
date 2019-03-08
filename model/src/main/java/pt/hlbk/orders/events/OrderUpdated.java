package pt.hlbk.orders.events;

import java.time.Instant;
import java.util.StringJoiner;

public class OrderUpdated implements OrderEvent {

    private final String eventId;
    private final String orderId;
    private final Integer quantity;
    private final String symbol;
    private final Instant created;

    public OrderUpdated(final String eventId,
                        final String orderId,
                        final Integer quantity,
                        final String symbol,
                        final Instant created) {
        this.eventId = eventId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.symbol = symbol;
        this.created = created;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderUpdated.class.getSimpleName() + "[", "]")
                .add("eventId='" + eventId + "'")
                .add("orderId='" + orderId + "'")
                .add("quantity=" + quantity)
                .add("symbol='" + symbol + "'")
                .add("created=" + created)
                .toString();
    }
}
