package com.example.billing.adapter.out.feign;

public record ProductResponse(Long id, String name, double price, int quantity) {
}
