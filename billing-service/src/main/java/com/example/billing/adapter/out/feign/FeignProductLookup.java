package com.example.billing.adapter.out.feign;

import com.example.billing.application.ProductDetails;
import com.example.billing.application.ProductLookup;
import org.springframework.stereotype.Component;

@Component
public class FeignProductLookup implements ProductLookup {
    private final ProductFeignClient client;

    public FeignProductLookup(ProductFeignClient client) { this.client = client; }

    @Override
    public ProductDetails findById(Long id) {
        ProductResponse product = client.findById(id);
        return new ProductDetails(product.id(), product.name(), product.price(), product.quantity());
    }
}
