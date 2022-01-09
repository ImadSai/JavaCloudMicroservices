package fr.myitworld.productservice.core.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @Column(unique=true)
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

}
