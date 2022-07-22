package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CustomerVO;
import com.example.demo.entity.Customer;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerVO saveCustomer(CustomerVO customerVO) throws Exception {
		Customer customer = Customer.builder()
				.customerId(customerVO.getCustomerId())
				.firstName(customerVO.getFirstName())
				.lastName(customerVO.getLastName())
				.address(customerVO.getAddress())
				.build();
		Customer savedCustomer = customerRepository.save(customer);
		return CustomerVO.builder()
				.customerId(savedCustomer.getCustomerId())
				.firstName(savedCustomer.getFirstName())
				.lastName(savedCustomer.getLastName())
				.address(savedCustomer.getAddress())
				.build();
	}

	@Override
	public List<CustomerVO> getCustomers() {
		List<Customer> customers = customerRepository.findAll();

		return customers.stream()
				.map(customer -> CustomerVO.builder()
						.customerId(customer.getCustomerId())
						.firstName(customer.getFirstName())
						.lastName(customer.getLastName())
						.address(customer.getAddress())
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public CustomerVO getCustomer(Integer customerId) {
		Customer customer = customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new NotFoundException("Could not find customer with customerId: " + customerId));

		return CustomerVO.builder()
				.customerId(customerId)
				.firstName(customer.getFirstName())
				.lastName(customer.getLastName())
				.address(customer.getAddress())
				.build();
	}

	@Override
	public void updateCustomer(Integer customerId, CustomerVO customerVO) throws Exception {
		Customer customer = customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new NotFoundException("Could not find customer with customerId: " + customerId));

		customer.setFirstName(customerVO.getFirstName());
		customer.setLastName(customerVO.getLastName());
		customer.setAddress(customerVO.getAddress());
		customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) throws Exception {
		Customer customer = customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new NotFoundException("Could not find customer with customerId: " + customerId));
		
		customerRepository.delete(customer);
	}
}
