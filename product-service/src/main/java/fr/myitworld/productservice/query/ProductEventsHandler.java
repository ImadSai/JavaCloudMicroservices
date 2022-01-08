package fr.myitworld.productservice.query;

import fr.myitworld.productservice.core.data.entity.Product;
import fr.myitworld.productservice.core.data.repository.ProductRepository;
import fr.myitworld.productservice.core.events.ProductCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    @Autowired
    private ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {

        Product product = new Product();
        BeanUtils.copyProperties(event, product);

        productRepository.save(product);

    }

}
