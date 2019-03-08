package pt.hlbk.actors.orders;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.hlbk.orders.events.OrderCreatedRequest;
import pt.hlbk.orders.events.OrderCreatedResponse;
import pt.hlbk.orders.events.OrderEventPersistRequest;
import pt.hlbk.orders.events.OrderEventPersistResponse;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OrderRouter extends AbstractActor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRouter.class);

    public static Props props(final ActorRef orderPersister) {
        return Props.create(OrderRouter.class, () -> new OrderRouter(orderPersister));
    }

    private final ActorRef orderPersister;
    private final ConcurrentMap<String, ActorRef> orderManagers;
    private final ConcurrentMap<String, ActorRef> inFlightListeners;

    private OrderRouter(final ActorRef orderPersister) {
        orderManagers = new ConcurrentHashMap<>();
        inFlightListeners = new ConcurrentHashMap<>();
        this.orderPersister = orderPersister;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(OrderCreatedRequest.class, req -> {
                    LOGGER.info(self() + ": Received " + req);
                    inFlightListeners.putIfAbsent(req.getOrderId(), getSender());
                    orderPersister.tell(OrderEventPersistRequest.of(req), getSelf());
                })
                .match(OrderEventPersistResponse.class, res -> {
                    LOGGER.info(self() + ": Received " + res);
                    final String orderId = res.getOrder().getOrderId();
                    final ActorRef orderManager = getContext().actorOf(OrderManager.props(), "order-manager-" + orderId);
                    //TODO should be a request and a response
                    orderManagers.put(orderId, orderManager);
                    orderManager.tell(res.getOrder(), getSelf());
                    inFlightListeners.get(orderId).tell(OrderCreatedResponse.of(res.getOrder()), getSelf());
                    inFlightListeners.remove(orderId);
                })
                .build();
    }
}
