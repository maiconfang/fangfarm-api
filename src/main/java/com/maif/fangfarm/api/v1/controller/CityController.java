package com.maif.fangfarm.api.v1.controller;

import java.util.Map;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maif.fangfarm.api.ResourceUriHelper;
import com.maif.fangfarm.api.v1.assembler.CityInputDisassembler;
import com.maif.fangfarm.api.v1.assembler.CityModelAssembler;
import com.maif.fangfarm.api.v1.model.CityModel;
import com.maif.fangfarm.api.v1.model.input.CityInput;
import com.maif.fangfarm.api.v1.openapi.controller.CityControllerOpenApi;
import com.maif.fangfarm.core.data.PageWrapper;
import com.maif.fangfarm.core.data.PageableTranslator;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.exception.StateNotFoundException;
import com.maif.fangfarm.domain.exception.BusinessException;
import com.maif.fangfarm.domain.filter.CityFilter;
import com.maif.fangfarm.domain.model.City;
import com.maif.fangfarm.domain.repository.CityRepository;
import com.maif.fangfarm.domain.service.RegisterCityService;
import com.maif.fangfarm.infrastructure.repository.spec.CitySpecs;

import org.springframework.hateoas.CollectionModel;

@RestController
@RequestMapping(path = "/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController implements CityControllerOpenApi {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegisterCityService registerCity;
	
	@Autowired
	private CityModelAssembler cityModelAssembler;
	
	@Autowired
	private CityInputDisassembler cityInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<City> pagedResourcesAssembler;
	
	@CheckSecurity.Cities.CanConsult
	@Override
	@GetMapping
	public PagedModel<CityModel> list(CityFilter filter, Pageable pageable) {
		
		Pageable pageableTranslate = translatePageable(pageable);
		Page<City> citiesPage = null;
		
		if(filter.getName()!=null || filter.getStateId()!=null  ) {
			citiesPage = cityRepository.findAll(CitySpecs.withFilter(filter), pageableTranslate);
		}
		
		else
			citiesPage = cityRepository.findAll(pageable);
		
		citiesPage = new PageWrapper<>(citiesPage, pageable);
		
		return pagedResourcesAssembler.toModel(citiesPage, cityModelAssembler);
	}
	
	@CheckSecurity.Cities.CanConsult
	@Override
	@GetMapping("/noPagination/{stateId}")
	public CollectionModel<CityModel> list(@PathVariable Long stateId) {
		
		List<City> allCities;
		
		if( stateId !=null  ) {
			 allCities = cityRepository.findAll(CitySpecs.withIdState(stateId));
		}
		else
			allCities =	cityRepository.findAll();
		
		return cityModelAssembler.toCollectionModel(allCities);
	}
	
	
	@CheckSecurity.Cities.CanConsult
	@Override
	@GetMapping("/{cityId}")
	public CityModel find(@PathVariable Long cityId) {
		City city = registerCity.findOrFail(cityId);
		
		return cityModelAssembler.toModel(city);
	}
	
	@CheckSecurity.Cities.CanEdit
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityModel add(@RequestBody @Valid CityInput cityInput) {
		try {
			City city = cityInputDisassembler.toDomainObject(cityInput);
			
			city = registerCity.save(city);
			
			CityModel cityModel = cityModelAssembler.toModel(city);
			
			ResourceUriHelper.addUriInResponseHeader(cityModel.getId());
			
			return cityModel;
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.Cities.CanEdit
	@Override
	@PutMapping("/{cityId}")
	public CityModel update(@PathVariable Long cityId, @RequestBody @Valid CityInput cityInput) {
		try {
			City currentCity = registerCity.findOrFail(cityId);
			
			cityInputDisassembler.copyToDomainObject(cityInput, currentCity);
			
			currentCity = registerCity.save(currentCity);
			
			return cityModelAssembler.toModel(currentCity);
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.Cities.CanEdit
	@Override
	@DeleteMapping("/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long cityId) {
		
		City city = registerCity.findOrFail(cityId);
		String nameCity = city.getName();
		
		registerCity.delete(cityId, nameCity);	
	}
	
	private Pageable translatePageable(Pageable apiPageable) {
		var mapping = Map.of(
				"id", "code",
				"name", "name"
			);
		
		return PageableTranslator.translate(apiPageable, mapping);
	}
	
}
