package fr.myitworld.orderservice.query;

import fr.myitworld.orderservice.core.data.entity.Order;
import fr.myitworld.orderservice.core.data.repository.OrderRepository;
import fr.myitworld.orderservice.core.events.OrderCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderEventsHandler {

    @Autowired
    private OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) {

        Order order = new Order();
        BeanUtils.copyProperties(event, order);

        orderRepository.save(order);
    }

}
