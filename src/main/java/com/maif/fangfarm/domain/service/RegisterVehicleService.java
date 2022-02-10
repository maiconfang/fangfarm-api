package com.maif.fangfarm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maif.fangfarm.domain.exception.EntityInUseException;
import com.maif.fangfarm.domain.exception.VehicleNotFoundException;
import com.maif.fangfarm.domain.model.Brand;
import com.maif.fangfarm.domain.model.Vehicle;
import com.maif.fangfarm.domain.repository.VehicleRepository;

@Service
public class RegisterVehicleService {

	private static final String MSG_VEHICLE_IN_USE_NAME 
	= "The vehicle with the name \""+ "%s" +"\" can't be removed, because it is in use";

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private RegisterBrandService registerBrand;
	
	@Transactional
	public Vehicle save(Vehicle vehicle) {
		Long brandId = vehicle.getBrand().getId();

		Brand brand = registerBrand.findOrFail(brandId);
		
		vehicle.setBrand(brand);
		
		return vehicleRepository.save(vehicle);
	}
	
	@Transactional
	public void delete(Long vehicleId, String nameVehicle) {
		try {
			vehicleRepository.deleteById(vehicleId);
			vehicleRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new VehicleNotFoundException(vehicleId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_VEHICLE_IN_USE_NAME, nameVehicle));
		}
	}
	
	public Vehicle findOrFail(Long vehicleId) {
		return vehicleRepository.findById(vehicleId)
			.orElseThrow(() -> new VehicleNotFoundException(vehicleId));
	}
	
}
