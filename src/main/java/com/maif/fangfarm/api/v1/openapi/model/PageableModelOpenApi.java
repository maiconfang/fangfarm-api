package com.maif.fangfarm.api.v1.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Pageable")
@Setter
@Getter
public class PageableModelOpenApi {

	@ApiModelProperty(example = "0", value = "Page number (starts at 0)")
	private int page;
	
	@ApiModelProperty(example = "10", value = "Number of elements per page")
	private int size;
	
	@ApiModelProperty(example = "name,asc", value = "Property name for sorting")
	private List<String> sort;
	
}
