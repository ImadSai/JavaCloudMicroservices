package fr.myitworld.productservice.command.lookup_entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "productlookup")
@AllArgsConstructor
@NoArgsConstructor
public class ProductLookupEntity {

    @Id
    private String productId;

    @Column(unique = true)
    private String name;

}
