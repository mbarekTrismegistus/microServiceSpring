package com.example.billing.adapter.out.feign;

import org.springframework.stereotype.Component;

@Component
public class ProductFeignFallback implements ProductFeignClient {
    @Override
    public ProductResponse findById(Long id) { return new ProductResponse(id, "Unavailable", 0.0, 0); }
}
