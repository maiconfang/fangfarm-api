package com.maif.fangfarm.domain.exception;

public abstract class EntityNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}
	
}
