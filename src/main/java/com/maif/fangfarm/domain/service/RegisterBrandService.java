package com.maif.fangfarm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maif.fangfarm.domain.exception.BrandNotFoundException;
import com.maif.fangfarm.domain.exception.EntityInUseException;
import com.maif.fangfarm.domain.model.Brand;
import com.maif.fangfarm.domain.model.Model;
import com.maif.fangfarm.domain.repository.BrandRepository;

@Service
public class RegisterBrandService {

	private static final String MSG_BRAND_IN_USE_NAME 
	= "The brand with the name \""+ "%s" +"\" can't be removed, because it is in use";

	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private RegisterModelService registerModel;
	
	@Transactional
	public Brand save(Brand brand) {
		Long modelId = brand.getModel().getId();

		Model model = registerModel.findOrFail(modelId);
		
		brand.setModel(model);
		
		return brandRepository.save(brand);
	}
	
	@Transactional
	public void delete(Long brandId, String nameBrand) {
		try {
			brandRepository.deleteById(brandId);
			brandRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new BrandNotFoundException(brandId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_BRAND_IN_USE_NAME, nameBrand));
		}
	}
	
	public Brand findOrFail(Long brandId) {
		return brandRepository.findById(brandId)
			.orElseThrow(() -> new BrandNotFoundException(brandId));
	}
	
}
