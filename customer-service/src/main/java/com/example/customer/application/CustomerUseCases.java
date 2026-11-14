package com.example.customer.application;

import com.example.customer.domain.Customer;
import com.example.customer.domain.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerUseCases {
    private final CustomerRepository customers;

    public CustomerUseCases(CustomerRepository customers) {
        this.customers = customers;
    }

    public List<Customer> findAll() { return customers.findAll(); }
    public Optional<Customer> findById(Long id) { return customers.findById(id); }
    public Customer create(Customer customer) { return customers.save(new Customer(null, customer.name(), customer.email())); }
    public Customer update(Long id, Customer customer) { return customers.save(new Customer(id, customer.name(), customer.email())); }
    public void delete(Long id) { customers.deleteById(id); }
}
