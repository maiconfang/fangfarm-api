package com.maif.fangfarm.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.MaifLinks;
import com.maif.fangfarm.api.v1.controller.CityController;
import com.maif.fangfarm.api.v1.model.CityModel;
import com.maif.fangfarm.core.security.MaifSecurity;
import com.maif.fangfarm.domain.model.City;

@Component
public class CityModelAssembler 
		extends RepresentationModelAssemblerSupport<City, CityModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaifLinks maifLinks;
	
	@Autowired
	private MaifSecurity maifSecurity;
	
	public CityModelAssembler() {
		super(CityController.class, CityModel.class);
	}
	
	@Override
	public CityModel toModel(City city) {
		CityModel cityModel = createModelWithId(city.getId(), city);
		
		modelMapper.map(city, cityModel);
		
		if (maifSecurity.canConsultCities()) {
			cityModel.add(maifLinks.linkToCities("cities"));
		}
		
		if (maifSecurity.canConsultStates()) {
			cityModel.getState().add(maifLinks.linkToState(cityModel.getState().getId()));
		}
		
		return cityModel;
	}
	
	@Override
	public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
		CollectionModel<CityModel> collectionModel = super.toCollectionModel(entities);
		
		if (maifSecurity.canConsultCities()) {
			collectionModel.add(maifLinks.linkToCities());
		}
		
		return collectionModel;
	}
	
}
