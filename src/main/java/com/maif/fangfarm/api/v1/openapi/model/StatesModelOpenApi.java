package com.maif.fangfarm.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.maif.fangfarm.api.v1.model.StateModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("StatesModel")
@Data
public class StatesModelOpenApi {

	private StatesEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("StatesEmbeddedModel")
	@Data
	public class StatesEmbeddedModelOpenApi {
		
		private List<StateModel> states;
		
	}
	
}
