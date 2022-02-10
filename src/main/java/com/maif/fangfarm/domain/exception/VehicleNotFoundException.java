package com.maif.fangfarm.domain.exception;

public class VehicleNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException(String message) {
		super(message);
	}
	
	public VehicleNotFoundException(Long vehicleId) {
		this(String.format("There is no register of the vehicle with a code %d", vehicleId));
	}
	
}
