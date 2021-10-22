package com.maif.fangfarm.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.maif.fangfarm.api.v1.model.GroupppModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("GroupsModel")
@Data
public class GroupsModelOpenApi {

	private GroupsEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("GroupsEmbeddedModel")
	@Data
	public class GroupsEmbeddedModelOpenApi {
		
		private List<GroupppModel> groups;
		
	}
	
}
