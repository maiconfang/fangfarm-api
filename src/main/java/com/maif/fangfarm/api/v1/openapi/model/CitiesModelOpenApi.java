package com.maif.fangfarm.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.maif.fangfarm.api.v1.model.CityModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CitiesModel")
@Data
public class CitiesModelOpenApi {

	private CitiesEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("CitiesEmbeddedModel")
	@Data
	public class CitiesEmbeddedModelOpenApi {
		
		private List<CityModel> cities;
		
	}
	
}
