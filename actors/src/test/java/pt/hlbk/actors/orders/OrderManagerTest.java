package pt.hlbk.actors.orders;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pt.hlbk.orders.CurrencyPair;
import pt.hlbk.orders.Order;
import pt.hlbk.orders.events.OrderTrackingStarted;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class OrderManagerTest {

    private static ActorSystem system;

    @BeforeAll
    static void init() {
        system = ActorSystem.create();
    }

    @AfterAll
    static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    void testIt() {
        final TestKit probe = new TestKit(system);
        final ActorRef deviceActor = system.actorOf(OrderManager.props());
        final Order order = new Order(
                UUID.randomUUID().toString(),
                1,
                CurrencyPair.of("EUR/USD"),
                Instant.now());
        deviceActor.tell(order, probe.getRef());


        final OrderTrackingStarted response = probe.expectMsgClass(OrderTrackingStarted.class);
        assertThat(response.getOrderId()).isEqualTo(order.getOrderId());
    }
}