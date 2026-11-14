package com.example.inventory.adapter.out.persistence;

import com.example.inventory.domain.Product;
import com.example.inventory.domain.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductPersistenceAdapter implements ProductRepository {
    private final SpringDataProductRepository repository;

    public ProductPersistenceAdapter(SpringDataProductRepository repository) { this.repository = repository; }

    @Override
    public List<Product> findAll() { return repository.findAll().stream().map(this::toDomain).toList(); }

    @Override
    public Optional<Product> findById(Long id) { return repository.findById(id).map(this::toDomain); }

    @Override
    public Product save(Product product) {
        return toDomain(repository.save(new ProductJpaEntity(product.id(), product.name(), product.price(), product.quantity())));
    }

    @Override
    public void deleteById(Long id) { repository.deleteById(id); }

    private Product toDomain(ProductJpaEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getPrice(), entity.getQuantity());
    }
}
