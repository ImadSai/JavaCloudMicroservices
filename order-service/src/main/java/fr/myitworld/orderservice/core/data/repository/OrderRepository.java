package fr.myitworld.orderservice.core.data.repository;

import fr.myitworld.orderservice.core.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
