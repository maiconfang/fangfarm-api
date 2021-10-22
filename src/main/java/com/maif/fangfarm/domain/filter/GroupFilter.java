package com.maif.fangfarm.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GroupFilter {

	@ApiModelProperty(example = "1", value = "ID of group of search")
	private Long groupId;
	
	@ApiModelProperty(example = "1", value = "Name of group of search")
	private String name;
	
	
}
