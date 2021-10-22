package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.model.input.EmployeeInput;
import com.maif.fangfarm.domain.model.City;
import com.maif.fangfarm.domain.model.Employee;

@Component
public class EmployeeInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Employee toDomainObject(EmployeeInput employeeInput) {
		return modelMapper.map(employeeInput, Employee.class);
	}
	
	public void copyToDomainObject(EmployeeInput employeeInput, Employee employee) {
		
		if (employee.getAddress() != null) {
			employee.getAddress().setCity(new City());
		}
		
		modelMapper.map(employeeInput, employee);
	}
	
}
