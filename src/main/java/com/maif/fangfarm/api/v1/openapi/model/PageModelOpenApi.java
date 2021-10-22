package com.maif.fangfarm.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PageModel")
@Getter
@Setter
public class PageModelOpenApi {

	@ApiModelProperty(example = "10", value = "Number of records per page")
	private Long size;
	
	@ApiModelProperty(example = "50", value = "Total de registros")
	private Long totalElements;
	
	@ApiModelProperty(example = "5", value = "Total records")
	private Long totalPages;
	
	@ApiModelProperty(example = "0", value = "Page number (starts at 0)")
	private Long number;
	
}
