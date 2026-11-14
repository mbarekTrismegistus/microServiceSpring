package com.example.billing.adapter.out.persistence;

import com.example.billing.domain.Bill;
import com.example.billing.domain.BillItem;
import com.example.billing.domain.BillRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BillPersistenceAdapter implements BillRepository {
    private final SpringDataBillRepository repository;

    public BillPersistenceAdapter(SpringDataBillRepository repository) { this.repository = repository; }

    @Override
    public Bill save(Bill bill) {
        List<BillItemJpaEntity> items = bill.items().stream()
                .map(item -> new BillItemJpaEntity(item.id(), item.productId(), item.quantity())).toList();
        return toDomain(repository.save(new BillJpaEntity(bill.id(), bill.customerId(), bill.billingDate(), items)));
    }

    @Override
    public List<Bill> findAll() { return repository.findAll().stream().map(this::toDomain).toList(); }

    @Override
    public Optional<Bill> findById(Long id) { return repository.findById(id).map(this::toDomain); }

    private Bill toDomain(BillJpaEntity entity) {
        List<BillItem> items = entity.getItems().stream()
                .map(item -> new BillItem(item.getId(), item.getProductId(), item.getQuantity())).toList();
        return new Bill(entity.getId(), entity.getCustomerId(), entity.getBillingDate(), items);
    }
}
