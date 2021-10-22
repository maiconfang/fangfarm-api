package com.maif.fangfarm.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "states")
@Setter
@Getter
public class StateModel extends RepresentationModel<StateModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "New Brunswick")
	private String name;
	
	@ApiModelProperty(example = "NB")
	private String fs;
	
	
}
