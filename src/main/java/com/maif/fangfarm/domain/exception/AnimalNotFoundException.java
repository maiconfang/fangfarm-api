package com.maif.fangfarm.domain.exception;

public class AnimalNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public AnimalNotFoundException(String message) {
		super(message);
	}
	
	public AnimalNotFoundException(Long stateId) {
		this(String.format("There is no register of the animal with a code %d", stateId));
	}
	
}
