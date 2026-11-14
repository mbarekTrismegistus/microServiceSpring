package com.example.billing.adapter.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", fallback = CustomerFeignFallback.class)
public interface CustomerFeignClient {
    @GetMapping("/customers/{id}")
    CustomerResponse findById(@PathVariable("id") Long id);
}
