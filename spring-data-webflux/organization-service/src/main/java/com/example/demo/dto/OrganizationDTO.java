package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrganizationDTO {
	private Integer id;
	private String name;
	private List<Employee> employees;
}
