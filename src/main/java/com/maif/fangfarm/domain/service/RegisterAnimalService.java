package com.maif.fangfarm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maif.fangfarm.domain.exception.AnimalNotFoundException;
import com.maif.fangfarm.domain.exception.EntityInUseException;
import com.maif.fangfarm.domain.model.Animal;
import com.maif.fangfarm.domain.repository.AnimalRepository;

@Service
public class RegisterAnimalService {

	private static final String MSG_ANIMAL_IN_USE_NAME 
	= "Animal with the name \""+ "%s" +"\" can´t be removed, because it is in use";
	
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Transactional
	public Animal save(Animal animal) {
		return animalRepository.save(animal);
	}
	
	@Transactional
	public void delete(Long animalId, String nameAnimal) {
		try {
			animalRepository.deleteById(animalId);
			animalRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new AnimalNotFoundException(animalId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_ANIMAL_IN_USE_NAME, nameAnimal));
		}
	}

	public Animal findOrFail(Long animalId) {
		return animalRepository.findById(animalId)
			.orElseThrow(() -> new AnimalNotFoundException(animalId));
	}
	
}
