package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.model.input.AnimalInput;
import com.maif.fangfarm.domain.model.Animal;

@Component
public class AnimalInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Animal toDomainObject(AnimalInput animalInput) {
		return modelMapper.map(animalInput, Animal.class);
	}
	
	public void copyToDomainObject(AnimalInput animalInput, Animal animal) {
		modelMapper.map(animalInput, animal);
	}
	
}
