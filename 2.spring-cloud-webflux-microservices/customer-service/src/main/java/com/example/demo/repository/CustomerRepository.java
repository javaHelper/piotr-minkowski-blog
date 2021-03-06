package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {

}