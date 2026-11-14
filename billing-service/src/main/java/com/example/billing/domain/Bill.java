package com.example.billing.domain;

import java.time.LocalDateTime;
import java.util.List;

public record Bill(Long id, Long customerId, LocalDateTime billingDate, List<BillItem> items) {
    public Bill {
        items = List.copyOf(items);
    }
}
