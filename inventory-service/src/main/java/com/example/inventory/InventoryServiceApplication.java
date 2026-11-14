package com.example.inventory;

import com.example.inventory.application.ProductUseCases;
import com.example.inventory.domain.Product;
import com.example.inventory.domain.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    ProductUseCases productUseCases(ProductRepository products) {
        return new ProductUseCases(products);
    }

    @Bean
    CommandLineRunner sampleProducts(ProductUseCases products) {
        return args -> {
            if (products.findAll().isEmpty()) {
                products.create(new Product(null, "Laptop", 899.99, 12));
                products.create(new Product(null, "Headphones", 79.99, 30));
            }
        };
    }
}
