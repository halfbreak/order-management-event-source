package pt.hlbk.launcher.runner;

import akka.actor.ActorRef;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pt.hlbk.orders.events.OrderCreatedRequest;

@Component
public class ActorStarter implements CommandLineRunner {

    private final ActorRef orderListener;

    public ActorStarter(ActorRef orderListener) {
        this.orderListener = orderListener;
    }

    @Override
    public void run(String... args) throws Exception {
        orderListener.tell(OrderCreatedRequest.create(1000, "EUR/USD"), orderListener);
    }
}
