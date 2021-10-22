package com.maif.fangfarm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maif.fangfarm.domain.exception.EntityInUseException;
import com.maif.fangfarm.domain.exception.GroupNotFoundException;
import com.maif.fangfarm.domain.model.Grouppp;
import com.maif.fangfarm.domain.model.Permission;
import com.maif.fangfarm.domain.repository.GroupRepository;

@Service
public class RegisterGroupService {

	private static final String MSG_GROUP_IN_USE_NAME 
	= "The group with the name \""+ "%s" +"\" can't be removed, because it is in use";
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private RegisterPermissionService registerPermission;
	
	@Transactional
	public Grouppp save(Grouppp group) {
		return groupRepository.save(group);
	}
	
	@Transactional
	public void delete(Long groupId, String nameGroup) {
		try {
			groupRepository.deleteById(groupId);
			groupRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new GroupNotFoundException(groupId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_GROUP_IN_USE_NAME, nameGroup));
		}
	}

	@Transactional
	public void disassociatePermission(Long groupId, Long permissionId) {
		Grouppp group = findOrFail(groupId);
		Permission permission = registerPermission.findOrFail(permissionId);
		
		group.removePermission(permission);
	}
	
	@Transactional
	public void associatePermission(Long groupId, Long permissionId) {
		Grouppp group = findOrFail(groupId);
		Permission permission = registerPermission.findOrFail(permissionId);
		
		group.addPermission(permission);
	}
	
	public Grouppp findOrFail(Long groupId) {
		return groupRepository.findById(groupId)
			.orElseThrow(() -> new GroupNotFoundException(groupId));
	}
	
}
