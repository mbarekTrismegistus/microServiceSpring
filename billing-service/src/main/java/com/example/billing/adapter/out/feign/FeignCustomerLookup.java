package com.example.billing.adapter.out.feign;

import com.example.billing.application.CustomerDetails;
import com.example.billing.application.CustomerLookup;
import org.springframework.stereotype.Component;

@Component
public class FeignCustomerLookup implements CustomerLookup {
    private final CustomerFeignClient client;

    public FeignCustomerLookup(CustomerFeignClient client) { this.client = client; }

    @Override
    public CustomerDetails findById(Long id) {
        CustomerResponse customer = client.findById(id);
        return new CustomerDetails(customer.id(), customer.name(), customer.email());
    }
}
