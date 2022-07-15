package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.demo.model.Organization;

public interface OrganizationRepository extends ReactiveCrudRepository<Organization, Integer>{

}
