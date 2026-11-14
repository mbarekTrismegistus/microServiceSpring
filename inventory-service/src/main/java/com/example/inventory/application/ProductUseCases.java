package com.example.inventory.application;

import com.example.inventory.domain.Product;
import com.example.inventory.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductUseCases {
    private final ProductRepository products;

    public ProductUseCases(ProductRepository products) { this.products = products; }

    public List<Product> findAll() { return products.findAll(); }
    public Optional<Product> findById(Long id) { return products.findById(id); }
    public Product create(Product product) { return products.save(new Product(null, product.name(), product.price(), product.quantity())); }
    public Product update(Long id, Product product) { return products.save(new Product(id, product.name(), product.price(), product.quantity())); }
    public void delete(Long id) { products.deleteById(id); }
}
