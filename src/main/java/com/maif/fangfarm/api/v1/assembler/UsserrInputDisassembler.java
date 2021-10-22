package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.model.input.UsserrInput;
import com.maif.fangfarm.domain.model.Usserr;

@Component
public class UsserrInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Usserr toDomainObject(UsserrInput usserrInput) {
		return modelMapper.map(usserrInput, Usserr.class);
	}
	
	public void copyToDomainObject(UsserrInput usserrInput, Usserr usserr) {
		modelMapper.map(usserrInput, usserr);
	}
	
}
