package fr.myitworld.productservice.command.lookup_events_handler;

import fr.myitworld.productservice.command.lookup_entity.ProductLookupEntity;
import fr.myitworld.productservice.command.lookup_repository.ProductLookupRepository;
import fr.myitworld.productservice.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

    @Autowired
    private ProductLookupRepository productLookupRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {

        ProductLookupEntity productLookupEntity = new ProductLookupEntity(event.getProductId(), event.getName());

        // Save in the Command Database
        productLookupRepository.save(productLookupEntity);
    }

}
