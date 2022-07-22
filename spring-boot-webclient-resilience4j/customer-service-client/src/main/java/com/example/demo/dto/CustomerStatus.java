package com.example.demo.dto;

public enum CustomerStatus {
	CREATED, UPDATED, DELETED;

	public String value() {
		return name();
	}

	public static CustomerStatus fromValue(String v) {
		return valueOf(v);
	}
}