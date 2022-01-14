package fr.myitworld.orderservice.core.events;

import fr.myitworld.orderservice.core.data.model.OrderStatus;
import lombok.Data;

@Data
public class OrderCreatedEvent {

    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;

}
