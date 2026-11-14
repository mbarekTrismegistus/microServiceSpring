package com.example.billing.adapter.out.persistence;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bills")
public class BillJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private LocalDateTime billingDate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bill_id")
    private List<BillItemJpaEntity> items = new ArrayList<>();

    protected BillJpaEntity() { }

    public BillJpaEntity(Long id, Long customerId, LocalDateTime billingDate, List<BillItemJpaEntity> items) {
        this.id = id;
        this.customerId = customerId;
        this.billingDate = billingDate;
        this.items = new ArrayList<>(items);
    }

    public Long getId() { return id; }
    public Long getCustomerId() { return customerId; }
    public LocalDateTime getBillingDate() { return billingDate; }
    public List<BillItemJpaEntity> getItems() { return items; }
}
