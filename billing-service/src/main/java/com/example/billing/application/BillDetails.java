package com.example.billing.application;

import java.time.LocalDateTime;
import java.util.List;

public record BillDetails(Long id, LocalDateTime billingDate, CustomerDetails customer, List<BillItemDetails> items) {
    public BillDetails {
        items = List.copyOf(items);
    }

    public record BillItemDetails(Long id, Long productId, String productName, double unitPrice, int quantity) { }
}
