package com.example.customer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, Long> {
}
