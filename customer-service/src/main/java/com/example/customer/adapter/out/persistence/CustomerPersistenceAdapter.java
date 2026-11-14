package com.example.customer.adapter.out.persistence;

import com.example.customer.domain.Customer;
import com.example.customer.domain.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerPersistenceAdapter implements CustomerRepository {
    private final SpringDataCustomerRepository repository;

    public CustomerPersistenceAdapter(SpringDataCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> findAll() { return repository.findAll().stream().map(this::toDomain).toList(); }

    @Override
    public Optional<Customer> findById(Long id) { return repository.findById(id).map(this::toDomain); }

    @Override
    public Customer save(Customer customer) {
        return toDomain(repository.save(new CustomerJpaEntity(customer.id(), customer.name(), customer.email())));
    }

    @Override
    public void deleteById(Long id) { repository.deleteById(id); }

    private Customer toDomain(CustomerJpaEntity entity) {
        return new Customer(entity.getId(), entity.getName(), entity.getEmail());
    }
}
