package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Customer;

@Transactional(readOnly = true)
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Optional<Customer> findByCustomerId(Integer customerId);
}