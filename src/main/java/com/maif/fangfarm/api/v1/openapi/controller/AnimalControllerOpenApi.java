package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.AnimalModel;
import com.maif.fangfarm.api.v1.model.input.AnimalInput;
import com.maif.fangfarm.domain.filter.AnimalFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Animal")
public interface AnimalControllerOpenApi {

	@ApiOperation("List the animals")
	PagedModel<AnimalModel> list(AnimalFilter filter, Pageable pageable);
	
	@ApiOperation("List the animals without pagination")
	CollectionModel<AnimalModel> listNoPagination(AnimalFilter filter);

	@ApiOperation("Find the animal by ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID of animal invalid", response = Problem.class),
		@ApiResponse(code = 404, message = "Animal not found", response = Problem.class)
	})
	AnimalModel find(
			@ApiParam(value = "ID of animal", example = "1", required = true)
			Long animalId);

	@ApiOperation("Register a animal")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Animal registered"),
	})
	AnimalModel add(
			@ApiParam(name = "body", value = "Representation of the new animal", required = true)
			AnimalInput animalInput);

	@ApiOperation("Update the animal by ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Animal updated"),
		@ApiResponse(code = 404, message = "Animal not found", response = Problem.class)
	})
	AnimalModel update(
			@ApiParam(value = "ID of the animal", example = "1", required = true)
			Long animalId,
			
			@ApiParam(name = "body", value = "Representation of the animal with new data", required = true)
			AnimalInput animalInput);

	@ApiOperation("Remove the animal by ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Animal removed"),
		@ApiResponse(code = 404, message = "Animal not found", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "ID of the animal", example = "1", required = true)
			Long animalId);

}