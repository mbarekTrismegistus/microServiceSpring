package com.example.customer;

import com.example.customer.application.CustomerUseCases;
import com.example.customer.domain.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner sampleCustomers(CustomerUseCases customers) {
        return args -> {
            if (customers.findAll().isEmpty()) {
                customers.create(new Customer(null, "John Doe", "john@example.com"));
                customers.create(new Customer(null, "Jane Doe", "jane@example.com"));
            }
        };
    }
}
