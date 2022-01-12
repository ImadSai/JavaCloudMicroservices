package fr.myitworld.productservice.command.controller;

import fr.myitworld.productservice.command.commands_list.CreateProductCommand;
import fr.myitworld.productservice.core.data.entity.Product;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    // Command Gateway
    @Autowired
    private CommandGateway commandGateway;

    /**
     * Create A Product
     *
     * @param product
     * @return
     */
    @PostMapping
    public String createProduct(@Valid @RequestBody Product product) {

        // Create a Product Command
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        return commandGateway.sendAndWait(createProductCommand);
    }
}
