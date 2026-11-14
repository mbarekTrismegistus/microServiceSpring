package com.example.billing;

import com.example.billing.application.BillUseCases;
import com.example.billing.application.CustomerLookup;
import com.example.billing.application.ProductLookup;
import com.example.billing.domain.BillRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    BillUseCases billUseCases(BillRepository bills, CustomerLookup customers, ProductLookup products) {
        return new BillUseCases(bills, customers, products);
    }
}
