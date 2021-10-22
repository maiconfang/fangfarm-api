package com.maif.fangfarm.domain.exception;

public class UsserrNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public UsserrNotFoundException(String message) {
		super(message);
	}
	
	public UsserrNotFoundException(Long idUser) {
		this(String.format("There is no user registration with code %d", idUser));
	}
	
}
