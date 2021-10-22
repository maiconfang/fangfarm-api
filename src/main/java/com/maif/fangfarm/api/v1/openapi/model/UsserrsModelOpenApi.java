package com.maif.fangfarm.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.maif.fangfarm.api.v1.model.UsserrModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("UsserrsModel")
@Data
public class UsserrsModelOpenApi {

	private UsserrsEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("UsserrsEmbeddedModel")
	@Data
	public class UsserrsEmbeddedModelOpenApi {
		
		private List<UsserrModel> usserrs;
		
	}
	
}
