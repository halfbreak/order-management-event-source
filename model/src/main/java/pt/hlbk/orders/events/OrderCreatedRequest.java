package pt.hlbk.orders.events;

import java.time.Instant;
import java.util.StringJoiner;
import java.util.UUID;

public class OrderCreatedRequest implements OrderEvent {

    private final String eventId;
    private final String orderId;
    private final Integer quantity;
    private final String symbol;
    private final Instant created;

    public OrderCreatedRequest(final String eventId,
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

    public static OrderCreatedRequest create(final Integer quantity,
                                             final String symbol) {
        return new OrderCreatedRequest(UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                quantity,
                symbol, Instant.now());
    }

    public String getOrderId() {
        return orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getSymbol() {
        return symbol;
    }

    public Instant getCreated() {
        return created;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", OrderCreatedRequest.class.getSimpleName() + "[", "]")
                .add("eventId='" + eventId + "'")
                .add("orderId='" + orderId + "'")
                .add("quantity=" + quantity)
                .add("symbol='" + symbol + "'")
                .add("created=" + created)
                .toString();
    }
}
