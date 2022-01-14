package fr.myitworld.orderservice.query;

import fr.myitworld.orderservice.core.data.entity.Order;
import fr.myitworld.orderservice.core.data.repository.OrderRepository;
import fr.myitworld.orderservice.query.queries_list.FindOrdersQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderQueryHandler {

    @Autowired
    private OrderRepository orderRepository;

    @QueryHandler
    public List<Order> findProducts(FindOrdersQuery findOrdersQuery) {
        return orderRepository.findAll();
    }


}
