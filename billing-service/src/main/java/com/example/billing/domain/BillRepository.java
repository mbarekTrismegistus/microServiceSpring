package com.example.billing.domain;

import java.util.List;
import java.util.Optional;

public interface BillRepository {
    Bill save(Bill bill);
    List<Bill> findAll();
    Optional<Bill> findById(Long id);
}
