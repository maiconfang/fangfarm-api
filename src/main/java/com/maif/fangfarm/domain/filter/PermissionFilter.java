package com.maif.fangfarm.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissionFilter {

	@ApiModelProperty(example = "1", value = "ID of permssion of search")
	private Long permissionId;
	
	@ApiModelProperty(example = "1", value = "Name of permission of search")
	private String name;
	
	@ApiModelProperty(example = "1", value = "Description of permission of search")
	private String description;
	
	
}
