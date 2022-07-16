package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FraudCheckHistory;
import com.example.demo.repository.FraudCheckHistoryRepository;

@Service
public class FraudCheckService {

	@Autowired
	private FraudCheckHistoryRepository checkHistoryRepository;
	
	public boolean isFraudulentCustomer(Integer customerId) {
        
		FraudCheckHistory fraudCheckHistory = checkHistoryRepository.findByCustomerId(customerId);
		if(fraudCheckHistory == null) {
			return false;
		}
		if(fraudCheckHistory.getFraudster()) {
			return true;
		}
        return false;
    }
}
