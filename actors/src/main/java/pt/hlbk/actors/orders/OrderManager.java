package pt.hlbk.actors.orders;

import akka.actor.AbstractActor;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.hlbk.orders.Order;

public class OrderManager extends AbstractActor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderManager.class);

    public static Props props() {
        return Props.create(OrderManager.class, OrderManager::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Order.class, order -> {
                    LOGGER.info(getSelf() + ": Received " + order);
                })
                .build();
    }
}
