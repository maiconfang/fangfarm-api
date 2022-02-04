package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.model.input.BrandInput;
import com.maif.fangfarm.domain.model.Brand;
import com.maif.fangfarm.domain.model.Model;

@Component
public class BrandInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Brand toDomainObject(BrandInput brandInput) {
		return modelMapper.map(brandInput, Brand.class);
	}
	
	public void copyToDomainObject(BrandInput brandInput, Brand brand) {
		brand.setModel(new Model());
		
		modelMapper.map(brandInput, brand);
	}
	
}
