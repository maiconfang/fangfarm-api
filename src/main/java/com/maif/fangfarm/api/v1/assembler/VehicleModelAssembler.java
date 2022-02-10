package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.controller.VehicleController;
import com.maif.fangfarm.api.v1.model.VehicleModel;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.domain.model.Vehicle;

@Component
public class VehicleModelAssembler 
		extends RepresentationModelAssemblerSupport<Vehicle, VehicleModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	public VehicleModelAssembler() {
		super(VehicleController.class, VehicleModel.class);
	}
	
	@Override
	public VehicleModel toModel(Vehicle vehicle) {
		VehicleModel vehicleModel = createModelWithId(vehicle.getId(), vehicle);
		
		modelMapper.map(vehicle, vehicleModel);
		
		if (maifSecurity.canConsultVehicles()) {
			vehicleModel.add(maifLinks.linkToVehicles("vehicles"));
		}
		
		if (maifSecurity.canConsultBrands()) {
			vehicleModel.getBrand().add(maifLinks.linkToBrand(vehicleModel.getBrand().getId()));
		}
		
		if (maifSecurity.canConsultVehicles()) {
			vehicleModel.getBrand().add(maifLinks.linkToVehicle(vehicleModel.getBrand().getId()));
		}
		
		return vehicleModel;
	}
	
	@Override
	public CollectionModel<VehicleModel> toCollectionModel(Iterable<? extends Vehicle> entities) {
		CollectionModel<VehicleModel> collectionModel = super.toCollectionModel(entities);
		
		if (maifSecurity.canConsultVehicles()) {
			collectionModel.add(maifLinks.linkToVehicles());
		}
		
		return collectionModel;
	}
	
}
