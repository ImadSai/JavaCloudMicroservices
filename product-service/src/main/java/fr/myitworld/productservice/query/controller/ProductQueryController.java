package fr.myitworld.productservice.query.controller;

import fr.myitworld.productservice.core.data.entity.Product;
import fr.myitworld.productservice.query.queries_list.FindProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    @Autowired
    QueryGateway queryGateway;

    /**
     * Get All products
     * @return list of products
     */
    @GetMapping()
    public List<Product> getAllProducts() {
        FindProductsQuery findProductsQuery = new FindProductsQuery();
        return queryGateway.query(findProductsQuery,
                ResponseTypes.multipleInstancesOf(Product.class)).join();
    }

}
