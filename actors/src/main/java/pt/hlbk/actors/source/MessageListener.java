package pt.hlbk.actors.source;

import pt.hlbk.orders.events.OrderEvent;

public interface MessageListener {
    void onMessage(OrderEvent orderEvent);
}
