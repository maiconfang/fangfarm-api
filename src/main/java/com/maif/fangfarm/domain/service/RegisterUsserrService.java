package com.maif.fangfarm.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maif.fangfarm.domain.exception.BusinessException;
import com.maif.fangfarm.domain.exception.UsserrNotFoundException;
import com.maif.fangfarm.domain.model.Grouppp;
import com.maif.fangfarm.domain.model.Usserr;
import com.maif.fangfarm.domain.repository.UsserrRepository;

@Service
public class RegisterUsserrService {

	@Autowired
	private UsserrRepository usserrRepository;
	
	@Autowired
	private RegisterGroupService registerGroup;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Usserr save(Usserr usserr) {
		usserrRepository.detach(usserr);
		
		Optional<Usserr> usserrExisting = usserrRepository.findByEmail(usserr.getEmail());
		
		if (usserrExisting.isPresent() && !usserrExisting.get().equals(usserr)) {
			throw new BusinessException(
					String.format("There is already a user registered with the email %s", usserr.getEmail()));
		}
		
		if (usserr.isNew()) {
			usserr.setPassword(passwordEncoder.encode(usserr.getPassword()));
		}
		
		return usserrRepository.save(usserr);
	} 
	
	@Transactional
	public void editPassword(Long usserrId, String currentPassword, String newPassword) {
		Usserr usserr = findOrFail(usserrId);
		
		if (!passwordEncoder.matches(currentPassword, usserr.getPassword())) {
			throw new BusinessException("Current password entered does not match user password");
		}
		
		usserr.setPassword(passwordEncoder.encode(newPassword));
	}

	@Transactional
	public void disassociateGroup(Long usserrId, Long groupId) {
		Usserr usserr = findOrFail(usserrId);
		Grouppp group = registerGroup.findOrFail(groupId);
		
		usserr.removeGroup(group);
	}
	
	@Transactional
	public void associateGroup(Long usserrId, Long groupId) {
		Usserr usserr = findOrFail(usserrId);
		Grouppp group = registerGroup.findOrFail(groupId);
		
		usserr.addGroup(group);
	}
	
	public Usserr findOrFail(Long usserrId) {
		return usserrRepository.findById(usserrId)
			.orElseThrow(() -> new UsserrNotFoundException(usserrId));
	}
	
}
