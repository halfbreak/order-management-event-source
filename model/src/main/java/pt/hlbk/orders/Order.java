package pt.hlbk.orders;

import pt.hlbk.orders.events.OrderCreatedRequest;

import java.time.Instant;
import java.util.StringJoiner;

public class Order {
    private final String orderId;
    private final Integer quantity;
    private final CurrencyPair currencyPair;
    private final Instant created;

    public Order(final String orderId,
                 final Integer quantity,
                 final CurrencyPair currencyPair,
                 final Instant created) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.currencyPair = currencyPair;
        this.created = created;
    }

    public static Order of(OrderCreatedRequest request) {
        return new Order(
                request.getOrderId(),
                request.getQuantity(),
                CurrencyPair.of(request.getSymbol()),
                request.getCreated()
        );
    }

    public String getOrderId() {
        return orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public CurrencyPair getCurrencyPair() {
        return currencyPair;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("orderId='" + orderId + "'")
                .add("quantity=" + quantity)
                .add("currencyPair=" + currencyPair)
                .add("created=" + created)
                .toString();
    }
}
