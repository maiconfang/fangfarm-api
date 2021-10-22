package com.maif.fangfarm.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ModelFilter {

	@ApiModelProperty(example = "1", value = "ID of model of search")
	private Long modelId;
	
	@ApiModelProperty(example = "1", value = "Name of model of search")
	private String name;
	
	
}
