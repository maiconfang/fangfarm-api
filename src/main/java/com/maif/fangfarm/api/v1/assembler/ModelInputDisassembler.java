package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.model.input.ModelInput;
import com.maif.fangfarm.domain.model.Model;

@Component
public class ModelInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Model toDomainObject(ModelInput modelInput) {
		return modelMapper.map(modelInput, Model.class);
	}
	
	public void copyToDomainObject(ModelInput modelInput, Model model) {
		modelMapper.map(modelInput, model);
	}
	
}
