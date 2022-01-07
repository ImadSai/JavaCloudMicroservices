package fr.myitworld.productservice;

import fr.myitworld.productservice.entities.Product;
import fr.myitworld.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {

        return args -> {
            productRepository.save(new Product(null, "Iphone 13", 900.00D, 5L));
            productRepository.save(new Product(null, "Macbook Pro", 1200.00D, 2L));
            productRepository.save(new Product(null, "Sony Camera", 1600.00D, 10L));
        };
    }
}
