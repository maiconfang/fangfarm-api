package com.maif.fangfarm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maif.fangfarm.domain.exception.EntityInUseException;
import com.maif.fangfarm.domain.exception.ModelNotFoundException;
import com.maif.fangfarm.domain.model.Model;
import com.maif.fangfarm.domain.repository.ModelRepository;

@Service
public class RegisterModelService {

	private static final String MSG_MODEL_IN_USE 
		= "The model with the code%d can't be removed, because it is in use";
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Transactional
	public Model save(Model model) {
		return modelRepository.save(model);
	}
	
	@Transactional
	public void delete(Long modelId) {
		try {
			modelRepository.deleteById(modelId);
			modelRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ModelNotFoundException(modelId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_MODEL_IN_USE, modelId));
		}
	}

	public Model findOrFail(Long modelId) {
		return modelRepository.findById(modelId)
			.orElseThrow(() -> new ModelNotFoundException(modelId));
	}
	
}
