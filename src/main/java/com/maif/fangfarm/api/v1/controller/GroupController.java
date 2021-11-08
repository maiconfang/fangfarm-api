package com.maif.fangfarm.api.v1.controller;

import java.util.Map;

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

import com.maif.fangfarm.api.v1.assembler.GroupppInputDisassembler;
import com.maif.fangfarm.api.v1.assembler.GroupppModelAssembler;
import com.maif.fangfarm.api.v1.model.GroupppModel;
import com.maif.fangfarm.api.v1.model.input.GroupppInput;
import com.maif.fangfarm.api.v1.openapi.controller.GroupControllerOpenApi;
import com.maif.fangfarm.core.data.PageWrapper;
import com.maif.fangfarm.core.data.PageableTranslator;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.filter.GroupFilter;
import com.maif.fangfarm.domain.model.Grouppp;
import com.maif.fangfarm.domain.repository.GroupRepository;
import com.maif.fangfarm.domain.service.RegisterGroupService;
import com.maif.fangfarm.infrastructure.repository.spec.GroupSpecs;

@RestController
@RequestMapping(path = "/v1/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController implements GroupControllerOpenApi {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private RegisterGroupService registerGroup;
	
	@Autowired
	private GroupppModelAssembler groupModelAssembler;
	
	@Autowired
	private GroupppInputDisassembler groupInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Grouppp> pagedResourcesAssembler;

	
	@CheckSecurity.UsersGroupPermission.CanConsult
	@Override
	@GetMapping
	public PagedModel<GroupppModel> list(GroupFilter filter, Pageable pageable) {
		
		Pageable pageableTranslate = translatePageable(pageable);
		Page<Grouppp> groupsPage = null;
		
		if(filter.getName()!=null ) {
			groupsPage = groupRepository.findAll(GroupSpecs.withName(filter), pageableTranslate);
		}
		else
			groupsPage = groupRepository.findAll(pageable);
		
		groupsPage = new PageWrapper<>(groupsPage, pageable);
		
		return pagedResourcesAssembler.toModel(groupsPage, groupModelAssembler);
	}
	
	
	@CheckSecurity.UsersGroupPermission.CanConsult
	@Override
	@GetMapping("/{groupId}")
	public GroupppModel find(@PathVariable Long groupId) {
		
		return groupModelAssembler.toModel(findGroup(groupId));
	}
	
	@CheckSecurity.UsersGroupPermission.CanEdit
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GroupppModel add(@RequestBody @Valid GroupppInput groupInput) {
		Grouppp group = groupInputDisassembler.toDomainObject(groupInput);
		
		group = registerGroup.save(group);
		
		return groupModelAssembler.toModel(group);
	}
	
	@CheckSecurity.UsersGroupPermission.CanEdit
	@Override
	@PutMapping("/{groupId}")
	public GroupppModel update(@PathVariable Long groupId,	@RequestBody @Valid GroupppInput groupInput) {
		Grouppp groupCorrent = registerGroup.findOrFail(groupId);
		
		groupInputDisassembler.copyToDomainObject(groupInput, groupCorrent);
		
		groupCorrent = registerGroup.save(groupCorrent);
		
		return groupModelAssembler.toModel(groupCorrent);
	}
	
	@CheckSecurity.UsersGroupPermission.CanEdit
	@Override
	@DeleteMapping("/{groupId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long groupId) {
		
		String nameGroup = findGroup(groupId).getName();
		
		registerGroup.delete(groupId, nameGroup);	
	}
	
	private Pageable translatePageable(Pageable apiPageable) {
		var mapping = Map.of(
				"id", "code",
				"name", "name"
			);
		
		return PageableTranslator.translate(apiPageable, mapping);
	}
	
	private Grouppp findGroup(Long groupId) {
		Grouppp group = registerGroup.findOrFail(groupId);
		return group;
	} 
	
}
