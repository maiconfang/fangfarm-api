package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.model.input.CityInput;
import com.maif.fangfarm.domain.model.City;
import com.maif.fangfarm.domain.model.State;

@Component
public class CityInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public City toDomainObject(CityInput cityInput) {
		return modelMapper.map(cityInput, City.class);
	}
	
	public void copyToDomainObject(CityInput cityInput, City city) {
		city.setState(new State());
		
		modelMapper.map(cityInput, city);
	}
	
}
