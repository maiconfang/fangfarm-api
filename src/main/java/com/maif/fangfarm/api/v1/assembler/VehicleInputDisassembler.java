package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.model.input.VehicleInput;
import com.maif.fangfarm.domain.model.Brand;
import com.maif.fangfarm.domain.model.Vehicle;

@Component
public class VehicleInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Vehicle toDomainObject(VehicleInput vehicleInput) {
		return modelMapper.map(vehicleInput, Vehicle.class);
	}
	
	public void copyToDomainObject(VehicleInput vehicleInput, Vehicle vehicle) {
		vehicle.setBrand(new Brand());
		
		modelMapper.map(vehicleInput, vehicle);
	}
	
}
