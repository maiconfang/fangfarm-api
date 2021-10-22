package com.maif.fangfarm.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cities")
@Setter
@Getter
public class CitySummaryModel extends RepresentationModel<CitySummaryModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Moncton")
	private String name;
	
	@ApiModelProperty(example = "New Brunswick")
	private String state;
	
}
