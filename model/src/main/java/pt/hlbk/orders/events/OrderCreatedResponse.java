package pt.hlbk.orders.events;

import pt.hlbk.orders.Order;

import java.time.Instant;
import java.util.StringJoiner;
import java.util.UUID;

public class OrderCreatedResponse implements OrderEvent {

    private final String eventId;
    private final String orderId;
    private final Integer quantity;
    private final String symbol;
    private final Instant created;

    public OrderCreatedResponse(final String eventId,
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

    public static OrderCreatedResponse of(Order order) {
        return new OrderCreatedResponse(
                UUID.randomUUID().toString(),
                order.getOrderId(),
                order.getQuantity(),
                order.getCurrencyPair().toSymbol(),
                Instant.now()
        );
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderCreatedResponse.class.getSimpleName() + "[", "]")
                .add("eventId='" + eventId + "'")
                .add("orderId='" + orderId + "'")
                .add("quantity=" + quantity)
                .add("symbol='" + symbol + "'")
                .add("created=" + created)
                .toString();
    }
}
