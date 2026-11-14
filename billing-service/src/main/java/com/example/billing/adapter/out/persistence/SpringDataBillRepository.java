package com.example.billing.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataBillRepository extends JpaRepository<BillJpaEntity, Long> {
}
