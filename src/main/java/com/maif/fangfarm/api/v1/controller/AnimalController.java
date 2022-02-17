package com.maif.fangfarm.api.v1.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
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

import com.maif.fangfarm.api.v1.assembler.AnimalInputDisassembler;
import com.maif.fangfarm.api.v1.assembler.AnimalModelAssembler;
import com.maif.fangfarm.api.v1.model.AnimalModel;
import com.maif.fangfarm.api.v1.model.input.AnimalInput;
import com.maif.fangfarm.api.v1.openapi.controller.AnimalControllerOpenApi;
import com.maif.fangfarm.core.data.PageWrapper;
import com.maif.fangfarm.core.data.PageableTranslator;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.filter.AnimalFilter;
import com.maif.fangfarm.domain.model.Animal;
import com.maif.fangfarm.domain.repository.AnimalRepository;
import com.maif.fangfarm.domain.service.RegisterAnimalService;
import com.maif.fangfarm.infrastructure.repository.spec.AnimalSpecs;

@RestController
@RequestMapping(path = "/v1/animals", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnimalController implements AnimalControllerOpenApi {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private RegisterAnimalService registerAnimal;
	
	@Autowired
	private AnimalModelAssembler animalModelAssembler;
	
	@Autowired
	private AnimalInputDisassembler animalInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Animal> pagedResourcesAssembler;
	
	
	@CheckSecurity.Animals.CanConsult
	@Override
	@GetMapping
	public PagedModel<AnimalModel> list(AnimalFilter filter, Pageable pageable) {
		
		Pageable pageableTranslate = translatePageable(pageable);
		Page<Animal> animalsPage = null;
		
		if(filter.getName()!=null ) {
			animalsPage = animalRepository.findAll(AnimalSpecs.withFilter(filter), pageableTranslate);
		}
		else
			animalsPage = animalRepository.findAll(pageable);
		
		animalsPage = new PageWrapper<>(animalsPage, pageable);
		
		return pagedResourcesAssembler.toModel(animalsPage, animalModelAssembler);
	}
	
	@CheckSecurity.Animals.CanConsult
	@GetMapping("/noPagination")
	public CollectionModel<AnimalModel> listNoPagination(AnimalFilter filter) {
		
		List<Animal> allAnimals;
		
		if(filter.getName()!=null ) {
			allAnimals = animalRepository.findAll(AnimalSpecs.withFilter(filter));
		}
		else
			allAnimals = animalRepository.findAll();
		
		return animalModelAssembler.toCollectionModel(allAnimals);
	}
	
	@CheckSecurity.Animals.CanConsult
	@Override
	@GetMapping("/{animalId}")
	public AnimalModel find(@PathVariable Long animalId) {
		Animal animal = registerAnimal.findOrFail(animalId);
		
		return animalModelAssembler.toModel(animal);
	}
	
	@CheckSecurity.Animals.CanEdit
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AnimalModel add(@RequestBody @Valid AnimalInput animalInput) {
		Animal animal = animalInputDisassembler.toDomainObject(animalInput);
		
		animal = registerAnimal.save(animal);
		
		return animalModelAssembler.toModel(animal);
	}
	
	@CheckSecurity.Animals.CanEdit
	@Override
	@PutMapping("/{animalId}")
	public AnimalModel update(@PathVariable Long animalId, @RequestBody @Valid AnimalInput animalInput) {
		Animal currentAnimal = registerAnimal.findOrFail(animalId);
		
		animalInputDisassembler.copyToDomainObject(animalInput, currentAnimal);
		
		currentAnimal = registerAnimal.save(currentAnimal);
		
		return animalModelAssembler.toModel(currentAnimal);
	}
	
	@CheckSecurity.Animals.CanEdit
	@Override
	@DeleteMapping("/{animalId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long animalId) {
		
		Animal animal = registerAnimal.findOrFail(animalId);
		String nameAnimal = animal.getName();
		
		registerAnimal.delete(animalId, nameAnimal);	
	}
	
	private Pageable translatePageable(Pageable apiPageable) {
		var mapping = Map.of(
				"id", "code",
				"name", "name"
			);
		
		return PageableTranslator.translate(apiPageable, mapping);
	}
	
}
