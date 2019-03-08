package pt.hlbk.actors.source;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.hlbk.orders.events.OrderCreatedRequest;
import pt.hlbk.orders.events.OrderCreatedResponse;
import pt.hlbk.orders.events.OrderEvent;

public class OrderListener extends AbstractActor implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderListener.class);

    public static Props props(final ActorRef orderRouter) {
        return Props.create(OrderListener.class, () -> new OrderListener(orderRouter));
    }

    private final ActorRef orderRouter;

    private OrderListener(final ActorRef orderRouter) {
        this.orderRouter = orderRouter;
    }

    @Override
    public void onMessage(OrderEvent orderEvent) {
        orderRouter.tell(orderEvent, getSelf());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(OrderCreatedRequest.class, req -> {
                    LOGGER.info(self() + ": Sending " + req);
                    orderRouter.tell(req, getSelf());
                })
                .match(OrderCreatedResponse.class, resp -> {
                    LOGGER.info(self() + ": Received " + resp);
                })
                .build();
    }

}
