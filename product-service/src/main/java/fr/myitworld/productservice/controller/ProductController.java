package fr.myitworld.productservice.controller;

import com.netflix.discovery.converters.Auto;
import fr.myitworld.productservice.command.CreateProductCommand;
import fr.myitworld.productservice.entities.Product;
import fr.myitworld.productservice.repository.ProductRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Product repository
    private ProductRepository productRepository;

    // Environment
    private Environment env;

    // Command Gateway
    private CommandGateway commandGateway;

    @Autowired
    public ProductController(ProductRepository productRepository, Environment env, CommandGateway commandGateway) {
        this.productRepository = productRepository;
        this.env = env;
        this.commandGateway = commandGateway;
    }

    /**
     * Get All products
     * @return list of products
     */
    @GetMapping(value = "/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Create A Product
     * @param product
     * @return
     */
    @PostMapping
    public String createProduct(@RequestBody Product product) {

        // Create a Product Command
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .id(UUID.randomUUID().toString())
                .build();

        String returnedValue;

        try {
            returnedValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception e ) {
            returnedValue = e.getMessage();
        }

        return returnedValue;
    }
}
