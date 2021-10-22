package com.maif.fangfarm.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public StateNotFoundException(String message) {
		super(message);
	}
	
	public StateNotFoundException(Long stateId) {
		this(String.format("There is no register of the state with a code %d", stateId));
	}
	
}
