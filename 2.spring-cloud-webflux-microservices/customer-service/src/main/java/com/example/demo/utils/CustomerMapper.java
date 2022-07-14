package com.example.demo.utils;

import java.util.List;

import com.example.demo.model.Customer;

public class CustomerMapper {

	public static Customer map(List<Customer> customers) {
		Customer customer = new Customer();
		for (Customer c : customers) {
			if (c.getAccounts() != null)
				customer.setAccounts(c.getAccounts());
			if (c.getAge() != 0)
				customer.setAge(c.getAge());
			if (c.getFirstName() != null)
				customer.setFirstName(c.getFirstName());
			if (c.getLastName() != null)
				customer.setLastName(c.getLastName());
			if(c.getId() != null)
				customer.setId(c.getId());
		}
		return customer;
	}

}