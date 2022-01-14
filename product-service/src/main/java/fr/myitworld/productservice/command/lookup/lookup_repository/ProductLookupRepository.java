package fr.myitworld.productservice.command.lookup.lookup_repository;

import fr.myitworld.productservice.command.lookup.lookup_entity.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {

    /**
     * Find by ProductId or Name
     *
     * @param productId
     * @param name
     * @return product if exists
     */
    ProductLookupEntity findByProductIdOrName(String productId, String name);

}
