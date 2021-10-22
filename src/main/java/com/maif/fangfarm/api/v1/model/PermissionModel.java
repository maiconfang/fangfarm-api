package com.maif.fangfarm.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "permssions")
@Setter
@Getter
public class PermissionModel extends RepresentationModel<PermissionModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "CONSULT_CITIES")
	private String name;
	
	@ApiModelProperty(example = "Allow consult cities")
	private String description;
	
}
