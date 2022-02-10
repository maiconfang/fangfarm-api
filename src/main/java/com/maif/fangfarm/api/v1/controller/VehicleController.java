package com.maif.fangfarm.api.v1.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maif.fangfarm.api.ResourceUriHelper;
import com.maif.fangfarm.api.v1.assembler.VehicleInputDisassembler;
import com.maif.fangfarm.api.v1.assembler.VehicleModelAssembler;
import com.maif.fangfarm.api.v1.model.VehicleModel;
import com.maif.fangfarm.api.v1.model.input.VehicleInput;
import com.maif.fangfarm.api.v1.openapi.controller.VehicleControllerOpenApi;
import com.maif.fangfarm.core.data.PageWrapper;
import com.maif.fangfarm.core.data.PageableTranslator;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.exception.BrandNotFoundException;
import com.maif.fangfarm.domain.exception.BusinessException;
import com.maif.fangfarm.domain.filter.VehicleFilter;
import com.maif.fangfarm.domain.model.Vehicle;
import com.maif.fangfarm.domain.repository.VehicleRepository;
import com.maif.fangfarm.domain.service.RegisterVehicleService;
import com.maif.fangfarm.infrastructure.repository.spec.VehicleSpecs;

@RestController
@RequestMapping(path = "/v1/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleController implements VehicleControllerOpenApi {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private RegisterVehicleService registerVehicle;

	@Autowired
	private VehicleModelAssembler vehicleModelAssembler;

	@Autowired
	private VehicleInputDisassembler vehicleInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Vehicle> pagedResourcesAssembler;

	@CheckSecurity.Vehicles.CanConsult
	@Override
	@GetMapping
	public PagedModel<VehicleModel> list(VehicleFilter filter, Pageable pageable) {

		Pageable pageableTranslate = translatePageable(pageable);
		Page<Vehicle> vehiclesPage = null;

		if (filter.getLicensePlate() != null || filter.getBrandId() != null) {
			vehiclesPage = vehicleRepository.findAll(VehicleSpecs.withFilter(filter), pageableTranslate);
		}

		else
			vehiclesPage = vehicleRepository.findAll(pageable);

		vehiclesPage = new PageWrapper<>(vehiclesPage, pageable);

		return pagedResourcesAssembler.toModel(vehiclesPage, vehicleModelAssembler);
	}

	@CheckSecurity.Vehicles.CanConsult
	@GetMapping("/noPagination")
	public CollectionModel<VehicleModel> listNoPagination(VehicleFilter filter) {

		List<Vehicle> allVehicles;

		if (filter.getLicensePlate() != null) {
			allVehicles = vehicleRepository.findAll(VehicleSpecs.withFilter(filter));
		} else
			allVehicles = vehicleRepository.findAll();

		return vehicleModelAssembler.toCollectionModel(allVehicles);
	}

	@CheckSecurity.Vehicles.CanConsult
	@Override
	@GetMapping("/noPagination/{brandId}")
	public CollectionModel<VehicleModel> list(@PathVariable Long brandId) {

		List<Vehicle> allVehicles;

		if (brandId != null) {
			allVehicles = vehicleRepository.findAll(VehicleSpecs.withIdBrand(brandId));
		} else
			allVehicles = vehicleRepository.findAll();

		return vehicleModelAssembler.toCollectionModel(allVehicles);
	}

	@CheckSecurity.Vehicles.CanConsult
	@Override
	@GetMapping("/{vehicleId}")
	public VehicleModel find(@PathVariable Long vehicleId) {
		Vehicle vehicle = registerVehicle.findOrFail(vehicleId);

		return vehicleModelAssembler.toModel(vehicle);
	}

	@CheckSecurity.Vehicles.CanEdit
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VehicleModel add(@RequestBody @Valid VehicleInput vehicleInput) {
		try {
			Vehicle vehicle = vehicleInputDisassembler.toDomainObject(vehicleInput);

			vehicle = registerVehicle.save(vehicle);

			VehicleModel vehicleModel = vehicleModelAssembler.toModel(vehicle);

			ResourceUriHelper.addUriInResponseHeader(vehicleModel.getId());

			return vehicleModel;
		} catch (BrandNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Vehicles.CanEdit
	@Override
	@PutMapping("/{vehicleId}")
	public VehicleModel update(@PathVariable Long vehicleId, @RequestBody @Valid VehicleInput vehicleInput) {
		try {
			Vehicle currentBrand = registerVehicle.findOrFail(vehicleId);

			vehicleInputDisassembler.copyToDomainObject(vehicleInput, currentBrand);

			currentBrand = registerVehicle.save(currentBrand);

			return vehicleModelAssembler.toModel(currentBrand);
		} catch (BrandNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Vehicles.CanEdit
	@Override
	@DeleteMapping("/{vehicleId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long vehicleId) {

		Vehicle vehicle = registerVehicle.findOrFail(vehicleId);
		String licensePlate = vehicle.getLicensePlate();

		registerVehicle.delete(vehicleId, licensePlate);
	}

	private Pageable translatePageable(Pageable apiPageable) {
		var mapping = Map.of(
				"id", "code",
				"color", "color",
				"year", "year",
				"lisencePlate", "license plate",
				"fuel", "fuel",
				"description", "description"
			);
		
		return PageableTranslator.translate(apiPageable, mapping);
	}
	

}
