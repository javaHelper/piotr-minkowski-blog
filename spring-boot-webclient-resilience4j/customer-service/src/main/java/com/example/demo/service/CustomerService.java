package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CustomerVO;

public interface CustomerService {
	CustomerVO saveCustomer(CustomerVO customerVO) throws Exception;
	List<CustomerVO> getCustomers();
	CustomerVO getCustomer(Integer customerId);
	void updateCustomer(Integer customerId, CustomerVO customerVO) throws Exception;
	void deleteCustomer(Integer customerId) throws Exception;
}