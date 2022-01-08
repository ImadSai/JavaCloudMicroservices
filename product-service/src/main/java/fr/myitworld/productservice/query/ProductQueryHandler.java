package fr.myitworld.productservice.query;

import fr.myitworld.productservice.core.data.entity.Product;
import fr.myitworld.productservice.core.data.repository.ProductRepository;
import fr.myitworld.productservice.query.queries_list.FindProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductQueryHandler {

    @Autowired
    private ProductRepository productRepository;

    @QueryHandler
    public List<Product> findProducts(FindProductsQuery findProductsQuery) {
        return  productRepository.findAll();
    }

}
