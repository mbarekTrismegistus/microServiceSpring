package com.example.inventory.domain;

public record Product(Long id, String name, double price, int quantity) {
}
