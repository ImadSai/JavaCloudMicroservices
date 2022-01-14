package fr.myitworld.orderservice.command.controller;

import fr.myitworld.orderservice.command.commands_list.CreateOrderCommand;
import fr.myitworld.orderservice.core.data.entity.Order;
import fr.myitworld.orderservice.core.data.model.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

    // Command Gateway
    @Autowired
    private CommandGateway commandGateway;

    // Query Gateway
    @Autowired
    private QueryGateway queryGateway;

    /**
     * Create An Order
     *
     * @param order
     * @return
     */
    @PostMapping
    public String createOrder(@Valid @RequestBody Order order) {

        String userId = "27b95829-4f3f-4ddf-8983-151ba010e35b";
        String orderId = UUID.randomUUID().toString();

        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder().addressId(order.getAddressId())
                .productId(order.getProductId()).userId(userId).quantity(order.getQuantity()).orderId(orderId)
                .orderStatus(OrderStatus.CREATED).build();

        return commandGateway.sendAndWait(createOrderCommand);
    }
}
