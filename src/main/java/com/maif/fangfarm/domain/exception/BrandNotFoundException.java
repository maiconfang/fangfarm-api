package com.maif.fangfarm.domain.exception;

public class BrandNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public BrandNotFoundException(String message) {
		super(message);
	}
	
	public BrandNotFoundException(Long brandId) {
		this(String.format("There is no register of the brand with a code %d", brandId));
	}
	
}
