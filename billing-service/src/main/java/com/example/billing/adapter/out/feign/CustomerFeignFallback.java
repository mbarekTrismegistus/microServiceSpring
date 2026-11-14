package com.example.billing.adapter.out.feign;

import org.springframework.stereotype.Component;

@Component
public class CustomerFeignFallback implements CustomerFeignClient {
    @Override
    public CustomerResponse findById(Long id) { return new CustomerResponse(id, "Unavailable", ""); }
}
