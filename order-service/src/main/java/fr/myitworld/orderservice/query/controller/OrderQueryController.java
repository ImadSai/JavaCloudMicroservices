package fr.myitworld.orderservice.query.controller;

import fr.myitworld.orderservice.core.data.entity.Order;
import fr.myitworld.orderservice.query.queries_list.FindOrdersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderQueryController {

    @Autowired
    QueryGateway queryGateway;

    /**
     * Get All products
     *
     * @return list of products
     */
    @GetMapping()
    public List<Order> getAllProducts() {
        FindOrdersQuery findProductsQuery = new FindOrdersQuery();
        return queryGateway.query(findProductsQuery,
                ResponseTypes.multipleInstancesOf(Order.class)).join();
    }

}
