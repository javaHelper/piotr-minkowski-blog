package com.example.demo.mapper;

import java.util.List;

import com.example.demo.dto.OrganizationDTO;

public class EmployeeMapper {

	public OrganizationDTO map(List<OrganizationDTO> organizationDTOs) {
		OrganizationDTO organizationDTO = new OrganizationDTO();
		for (OrganizationDTO o : organizationDTOs) {
			if(o.getId() != 0) {
				organizationDTO.setId(o.getId());
			}
			if(o.getName() != null) {
				organizationDTO.setName(o.getName());
			}
			if(o.getEmployees() != null) {
				organizationDTO.setEmployees(o.getEmployees());
			}
		}
		return organizationDTO;
	}
}
