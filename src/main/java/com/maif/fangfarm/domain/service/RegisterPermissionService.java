package com.maif.fangfarm.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maif.fangfarm.domain.exception.PermissionNotFoundException;
import com.maif.fangfarm.domain.model.Permission;
import com.maif.fangfarm.domain.repository.PermissionRepository;

@Service
public class RegisterPermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public Permission findOrFail(Long permissionId) {
		return permissionRepository.findById(permissionId)
			.orElseThrow(() -> new PermissionNotFoundException(permissionId));
	}
	
}
