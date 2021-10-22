package com.maif.fangfarm.domain.exception;

public class EmployeeNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
	public EmployeeNotFoundException(Long employeeId) {
		this(String.format("There is no register of employee with the code %d", employeeId));
	}
	
}
