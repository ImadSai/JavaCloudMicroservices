package fr.myitworld.productservice.command;

import fr.myitworld.productservice.core.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate() {}

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {

        // Validate Product Price
        if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be les or equal than Zero");
        }

        // Validate Product Name
        if(createProductCommand.getName() == null || createProductCommand.getName().isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        // We create ProductCreatedEvent and copy properties
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        // Publish the event
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {

        this.id = productCreatedEvent.getId();
        this.name = productCreatedEvent.getName();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();


    }
}
