package com.maif.fangfarm.domain.exception;

public class EmployeeNotFondException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFondException(String message) {
		super(message);
	}
	
	public EmployeeNotFondException(Long employeeId) {
		this(String.format("There is no register of employee with the code %d", employeeId));
	}
	
}
