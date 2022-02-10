package com.maif.fangfarm.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "vehicles")
@Setter
@Getter
public class VehicleModel extends RepresentationModel<VehicleModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Green")
	private String color;
	
	@ApiModelProperty(example = "2021-2022")
	private String year;
	
	@ApiModelProperty(example = "MAIF-123")
	private String licensePlate;
	
	@ApiModelProperty(example = "Diesel")
	private String fuel;
	
	@ApiModelProperty(example = "Versatile MFWD")
	private String description;
	
	private BrandModel brand;
	
}
