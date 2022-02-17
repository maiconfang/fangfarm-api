package com.maif.fangfarm.api.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.stereotype.Component;

import com.maif.fangfarm.api.v1.controller.AnimalController;
import com.maif.fangfarm.api.v1.controller.BrandController;
import com.maif.fangfarm.api.v1.controller.CityController;
import com.maif.fangfarm.api.v1.controller.EmployeeController;
import com.maif.fangfarm.api.v1.controller.GroupController;
import com.maif.fangfarm.api.v1.controller.GroupPermissionController;
import com.maif.fangfarm.api.v1.controller.ModelController;
import com.maif.fangfarm.api.v1.controller.PermissionController;
import com.maif.fangfarm.api.v1.controller.StateController;
import com.maif.fangfarm.api.v1.controller.UserGroupController;
import com.maif.fangfarm.api.v1.controller.UsserrController;
import com.maif.fangfarm.api.v1.controller.VehicleController;

@Component
public class MaifLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	
	public static final TemplateVariables PROJECAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("projection", VariableType.REQUEST_PARAM));
	
	
	public Link linkToUsserr(Long usserrId, String rel) {
		return linkTo(methodOn(UsserrController.class)
				.find(usserrId)).withRel(rel);
	}
	
	public Link linkToUsserr(Long usserrId) {
		return linkToUsserr(usserrId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToUsserrs(String rel) {
		return linkTo(UsserrController.class).withRel(rel);
	}
	
	public Link linkToUsserrs() {
		return linkToUsserrs(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToUsserrGropuAssociation(Long usserrId, String rel) {
		return linkTo(methodOn(UserGroupController.class)
				.associate(usserrId, null)).withRel(rel);
	}
	
	public Link linkToUsserrGroupDisfellowshipping(Long usserrId, Long groupId, String rel) {
		return linkTo(methodOn(UserGroupController.class)
				.disassociate(usserrId, groupId)).withRel(rel);
	}
	
	public Link linkToGroupsUsserr(Long usserrId, String rel) {
		return linkTo(methodOn(UserGroupController.class)
				.list(usserrId)).withRel(rel);
	}
	
	public Link linkToGroupsUsserrs(Long usserrId) {
		return linkToGroupsUsserr(usserrId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGroups(String rel) {
		return linkTo(GroupController.class).withRel(rel);
	}
	
	public Link linkToGroups() {
		return linkToGroups(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToPermissions(String rel) {
		return linkTo(PermissionController.class).withRel(rel);
	}
	
	public Link linkToPermissions() {
		return linkToPermissions(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGroupPermissions(Long groupId, String rel) {
		return linkTo(methodOn(GroupPermissionController.class)
				.list(groupId)).withRel(rel);
	}
	
	public Link linkToGrupoPermissions(Long groupId) {
		return linkToGroupPermissions(groupId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToGroupPermissionAssociation(Long groupId, String rel) {
		return linkTo(methodOn(GroupPermissionController.class)
				.associate(groupId, null)).withRel(rel);
	}
	
	public Link linkToGropuPermissionDisassociate(Long groupId, Long permissionId, String rel) {
		return linkTo(methodOn(GroupPermissionController.class)
				.disassociate(groupId, permissionId)).withRel(rel);
	}
	
	public Link linkToCities(Long cityId, String rel) {
		return linkTo(methodOn(CityController.class)
				.find(cityId)).withRel(rel);
	}
	
	public Link linkToCity(Long cityId) {
		return linkToCities(cityId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCities(String rel) {
		return linkTo(CityController.class).withRel(rel);
	}
	
	public Link linkToCities() {
		return linkToCities(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToState(Long stateId, String rel) {
		return linkTo(methodOn(StateController.class)
				.find(stateId)).withRel(rel);
	}
	
	public Link linkToState(Long stateId) {
		return linkToState(stateId, IanaLinkRelations.SELF.value());
	}

	
	public Link linkToState(String rel) {
		return linkTo(StateController.class).withRel(rel);
	}
	
	
	public Link linkToStates() {
		return linkToState(IanaLinkRelations.SELF.value());
	}
	
	
	public Link linkToModel(Long modelId, String rel) {
		return linkTo(methodOn(ModelController.class)
				.find(modelId)).withRel(rel);
	}
	
	public Link linkToModel(Long modelId) {
		return linkToModel(modelId, IanaLinkRelations.SELF.value());
	}

	
	public Link linkToModel(String rel) {
		return linkTo(ModelController.class).withRel(rel);
	}
	
	public Link linkToModels() {
		return linkToModel(IanaLinkRelations.SELF.value());
	}
	
	public Link linkToEmployees(String rel) {
		return linkTo(EmployeeController.class).withRel(rel);
	}

	public Link linkToEmployees() {
		return linkToEmployees(IanaLinkRelations.SELF.value());
	}
	
	
	// Brand
	public Link linkToBrand(Long brandId, String rel) {
		return linkTo(methodOn(BrandController.class)
				.find(brandId)).withRel(rel);
	}
	
	public Link linkToBrand(Long brandId) {
		return linkToBrand(brandId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToBrands(String rel) {
		return linkTo(BrandController.class).withRel(rel);
	}
	
	public Link linkToBrands() {
		return linkToBrands(IanaLinkRelations.SELF.value());
	}
	
	// Vehicle
	public Link linkToVehicle(Long vehicleId, String rel) {
		return linkTo(methodOn(VehicleController.class)
				.find(vehicleId)).withRel(rel);
	}
	
	public Link linkToVehicle(Long vehicleId) {
		return linkToVehicle(vehicleId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToVehicles(String rel) {
		return linkTo(VehicleController.class).withRel(rel);
	}
	
	public Link linkToVehicles() {
		return linkToVehicles(IanaLinkRelations.SELF.value());
	}
	
	// Animal
	public Link linkToAnimal(Long animalId, String rel) {
		return linkTo(methodOn(AnimalController.class)
				.find(animalId)).withRel(rel);
	}
	
	public Link linkToAnimal(Long animalId) {
		return linkToAnimal(animalId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToAnimals(String rel) {
		return linkTo(AnimalController.class).withRel(rel);
	}
	
	public Link linkToAnimals() {
		return linkToAnimals(IanaLinkRelations.SELF.value());
	}
	
}
