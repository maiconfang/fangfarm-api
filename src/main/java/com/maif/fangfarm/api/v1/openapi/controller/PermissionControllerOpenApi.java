package com.maif.fangfarm.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.maif.fangfarm.api.v1.model.PermissionModel;
import com.maif.fangfarm.domain.filter.PermissionFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permisssions")
public interface PermissionControllerOpenApi {

	@ApiOperation("List the permissions")
	PagedModel<PermissionModel> list(PermissionFilter filter, Pageable pageable);
	
}
