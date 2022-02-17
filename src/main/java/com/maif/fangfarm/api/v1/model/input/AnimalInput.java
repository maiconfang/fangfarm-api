package com.maif.fangfarm.api.v1.model.input;

import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnimalInput {

	@ApiModelProperty(example = "1", required = true)
	private Long id;
	
	@ApiModelProperty(example = "Amora", required = true)
	private String name;
	
	@ApiModelProperty(example = "IDEN-0548752", required = true)
	private String identification;
	
	@ApiModelProperty(example = "Female", required = true)
	private String sex;
	
	@ApiModelProperty(example = "5", required = true)
	private Integer age;
	
	@ApiModelProperty(example = "Maine Coon", required = true)
	private String breed;

	@ApiModelProperty(example = "2016-11-01", required = true)
	private OffsetDateTime dtBirthday;
	
}
