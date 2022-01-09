package fr.myitworld.productservice.core.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @Column(unique = true)
    private String productId;

    @NotBlank(message = "Product name is required")
    private String name;

    @DecimalMin(value = "0.01", message = "Product price cannot be less than 0.01")
    private BigDecimal price;

    @Min(value = 0, message = "Product quantity cannot be less than 0")
    private Integer quantity;

}
