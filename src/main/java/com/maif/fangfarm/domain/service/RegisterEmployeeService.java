package com.maif.fangfarm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maif.fangfarm.domain.exception.EntityInUseException;
import com.maif.fangfarm.domain.exception.EmployeeNotFondException;
import com.maif.fangfarm.domain.model.City;
import com.maif.fangfarm.domain.model.Employee;
import com.maif.fangfarm.domain.repository.EmployeeRepository;

@Service
public class RegisterEmployeeService {

	private static final String MSG_EMPLOYEE_IN_USE = "The employee with the code %d can't be removed, because it is in use";

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RegisterCityService registerCity;
	
	@Transactional
	public Employee save(Employee employee) {
		Long cityId = employee.getAddress().getCity().getId();

		City city = registerCity.findOrFail(cityId);
		
		employee.getAddress().setCity(city);
		
		return employeeRepository.save(employee);
	}
	
	@Transactional
	public void delete(Long employeeId) {
		try {
			employeeRepository.deleteById(employeeId);
			employeeRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new EmployeeNotFondException(employeeId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_EMPLOYEE_IN_USE, employeeId));
		}
	}
	
	public Employee findOrFail(Long employeeId) {
		return employeeRepository.findById(employeeId)
			.orElseThrow(() -> new EmployeeNotFondException(employeeId));
	}
	
}
