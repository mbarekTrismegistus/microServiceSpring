package com.example.billing.application;

import com.example.billing.domain.Bill;
import com.example.billing.domain.BillItem;
import com.example.billing.domain.BillRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillUseCases {
    private final BillRepository bills;
    private final CustomerLookup customers;
    private final ProductLookup products;

    public BillUseCases(BillRepository bills, CustomerLookup customers, ProductLookup products) {
        this.bills = bills;
        this.customers = customers;
        this.products = products;
    }

    public BillDetails create(Long customerId, List<BillItem> items) {
        Bill bill = bills.save(new Bill(null, customerId, LocalDateTime.now(), items));
        return toDetails(bill);
    }

    public List<BillDetails> findAll() { return bills.findAll().stream().map(this::toDetails).toList(); }

    public Optional<BillDetails> findById(Long id) { return bills.findById(id).map(this::toDetails); }

    private BillDetails toDetails(Bill bill) {
        CustomerDetails customer = customers.findById(bill.customerId());
        List<BillDetails.BillItemDetails> items = bill.items().stream().map(item -> {
            ProductDetails product = products.findById(item.productId());
            return new BillDetails.BillItemDetails(item.id(), product.id(), product.name(), product.price(), item.quantity());
        }).toList();
        return new BillDetails(bill.id(), bill.billingDate(), customer, items);
    }
}
