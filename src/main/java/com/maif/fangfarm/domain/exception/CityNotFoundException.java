package com.maif.fangfarm.domain.exception;

public class CityNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public CityNotFoundException(String message) {
		super(message);
	}
	
	public CityNotFoundException(Long cityId) {
		this(String.format("There is no register of the city with a code %d", cityId));
	}
	
}
