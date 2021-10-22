package com.maif.fangfarm.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problem")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	
	@ApiModelProperty(example = "2019-12-01T18:09:02.70844Z", position = 5)
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(example = "http://localhost:8080/invalid-data", position = 10)
	private String type;
	
	@ApiModelProperty(example = "Invalid data", position = 15)
	private String title;
	
	@ApiModelProperty(example = "One or more fields are invalid. Fill in correctly and try again.", 
			position = 20)
	private String detail;
	
	@ApiModelProperty(example = "One or more fields are invalid. Fill in correctly and try again.", 
			position = 25)
	private String userMessage;
	
	@ApiModelProperty(value = "List of objects or fields that generated the error (optional)", 
			position = 30)
	private List<Object> objects;
	
	
}
