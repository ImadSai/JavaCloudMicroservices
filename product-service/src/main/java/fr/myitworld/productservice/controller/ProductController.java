package fr.myitworld.productservice.controller;

import fr.myitworld.productservice.entities.Product;
import fr.myitworld.productservice.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProductController {

    // Produit repository
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get All products
     * @return list of products
     */
    @GetMapping(value = "/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
