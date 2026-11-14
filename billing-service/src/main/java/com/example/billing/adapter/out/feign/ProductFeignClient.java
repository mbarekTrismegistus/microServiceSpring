package com.example.billing.adapter.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", fallback = ProductFeignFallback.class)
public interface ProductFeignClient {
    @GetMapping("/products/{id}")
    ProductResponse findById(@PathVariable("id") Long id);
}
