package com.example.billing.application;

public interface CustomerLookup {
    CustomerDetails findById(Long id);
}
