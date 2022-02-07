package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;

import com.maif.fangfarm.api.exceptionhandler.Problem;
import com.maif.fangfarm.api.v1.model.ModelModel;
import com.maif.fangfarm.api.v1.model.input.ModelInput;
import com.maif.fangfarm.domain.filter.ModelFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Models")
public interface ModelControllerOpenApi {

	@ApiOperation("List of models")
	PagedModel<ModelModel> list(ModelFilter filter, Pageable pageable);
	
	@ApiOperation("List the models without pagination")
	CollectionModel<ModelModel> listNoPagination(ModelFilter filter);

	@ApiOperation("Find a model by ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID of model invalid", response = Problem.class),
		@ApiResponse(code = 404, message = "Model not found", response = Problem.class)
	})
	ModelModel find(
			@ApiParam(value = "ID of model", example = "1", required = true)
			Long modelId);

	@ApiOperation("Register a model")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Model registered"),
	})
	ModelModel add(
			@ApiParam(name = "body", value = "Representation of the new model", required = true)
			ModelInput modelInput);

	@ApiOperation("Update a model by ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Model updated"),
		@ApiResponse(code = 404, message = "Model not found", response = Problem.class)
	})
	ModelModel update(
			@ApiParam(value = "ID of model", example = "1", required = true)
			Long modelId,
			
			@ApiParam(name = "body", value = "Representation of a new model with new data", required = true)
			ModelInput modelInput);

	@ApiOperation("Remove the model by ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Model removed"),
		@ApiResponse(code = 404, message = "Model not found", response = Problem.class)
	})
	void remove(
			@ApiParam(value = "ID of model", example = "1", required = true)
			Long modelId);

}