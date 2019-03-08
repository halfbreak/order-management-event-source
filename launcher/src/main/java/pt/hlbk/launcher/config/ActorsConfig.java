package pt.hlbk.launcher.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.hlbk.actors.orders.OrderPersister;
import pt.hlbk.actors.orders.OrderRouter;
import pt.hlbk.actors.source.OrderListener;

@Configuration
public class ActorsConfig {

    @Bean
    ActorSystem orderActorSystem() {
        return ActorSystem.create("orders");
    }

    @Bean
    ActorRef orderPersister(final ActorSystem orderActorSystem) {
        return orderActorSystem.actorOf(OrderPersister.props(), "order-persister");
    }

    @Bean
    ActorRef orderRouter(final ActorSystem orderActorSystem,
                         final ActorRef orderPersister) {
        return orderActorSystem.actorOf(OrderRouter.props(orderPersister), "order-router");
    }

    @Bean
    ActorRef orderListener(final ActorSystem orderActorSystem,
                           final ActorRef orderRouter) {
        return orderActorSystem.actorOf(OrderListener.props(orderRouter), "order-listener");
    }
}
