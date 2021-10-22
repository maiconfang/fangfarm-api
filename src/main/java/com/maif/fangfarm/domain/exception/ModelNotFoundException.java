package com.maif.fangfarm.domain.exception;

public class ModelNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public ModelNotFoundException(String message) {
		super(message);
	}
	
	public ModelNotFoundException(Long modelId) {
		this(String.format("There is no register of the model with the code %d", modelId));
	}
	
}
