package pt.hlbk.actors.orders;

import akka.actor.AbstractActor;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.hlbk.orders.Order;
import pt.hlbk.orders.events.OrderCreatedRequest;
import pt.hlbk.orders.events.OrderEventPersistRequest;
import pt.hlbk.orders.events.OrderEventPersistResponse;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OrderPersister extends AbstractActor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPersister.class);

    public static Props props() {
        return Props.create(OrderPersister.class, OrderPersister::new);
    }

    private final ConcurrentMap<String, Order> database;

    private OrderPersister() {
        this.database = new ConcurrentHashMap<>();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(OrderEventPersistRequest.class, event -> {
                    LOGGER.info(self() + ": Persisting " + event);
                    if (event.getOrderEvent() instanceof OrderCreatedRequest) {
                        final Order order = Order.of((OrderCreatedRequest) event.getOrderEvent());
                        database.putIfAbsent(order.getOrderId(), order);
                        getSender().tell(OrderEventPersistResponse.of(order), getSelf());
                    }
                })
                .build();
    }
}
