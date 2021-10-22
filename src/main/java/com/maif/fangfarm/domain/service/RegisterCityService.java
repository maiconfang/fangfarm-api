package com.maif.fangfarm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maif.fangfarm.domain.exception.CityNotFoundException;
import com.maif.fangfarm.domain.exception.EntityInUseException;
import com.maif.fangfarm.domain.model.City;
import com.maif.fangfarm.domain.model.State;
import com.maif.fangfarm.domain.repository.CityRepository;

@Service
public class RegisterCityService {

	private static final String MSG_CITY_IN_USE_NAME 
	= "The city with the name \""+ "%s" +"\" can't be removed, because it is in use";

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegisterStateService registerState;
	
	@Transactional
	public City save(City city) {
		Long stateId = city.getState().getId();

		State state = registerState.findOrFail(stateId);
		
		city.setState(state);
		
		return cityRepository.save(city);
	}
	
	@Transactional
	public void delete(Long cityId, String nameCity) {
		try {
			cityRepository.deleteById(cityId);
			cityRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFoundException(cityId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_CITY_IN_USE_NAME, nameCity));
		}
	}
	
	public City findOrFail(Long cityId) {
		return cityRepository.findById(cityId)
			.orElseThrow(() -> new CityNotFoundException(cityId));
	}
	
}
