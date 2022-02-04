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

import com.maif.fangfarm.api.ResourceUriHelper;
import com.maif.fangfarm.api.v1.assembler.BrandInputDisassembler;
import com.maif.fangfarm.api.v1.assembler.BrandModelAssembler;
import com.maif.fangfarm.api.v1.model.BrandModel;
import com.maif.fangfarm.api.v1.model.input.BrandInput;
import com.maif.fangfarm.api.v1.openapi.controller.BrandControllerOpenApi;
import com.maif.fangfarm.core.data.PageWrapper;
import com.maif.fangfarm.core.data.PageableTranslator;
import com.maif.fangfarm.core.security.CheckSecurity;
import com.maif.fangfarm.domain.exception.BusinessException;
import com.maif.fangfarm.domain.exception.ModelNotFoundException;
import com.maif.fangfarm.domain.filter.BrandFilter;
import com.maif.fangfarm.domain.model.Brand;
import com.maif.fangfarm.domain.repository.BrandRepository;
import com.maif.fangfarm.domain.service.RegisterBrandService;
import com.maif.fangfarm.infrastructure.repository.spec.BrandSpecs;

@RestController
@RequestMapping(path = "/v1/brands", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandController implements BrandControllerOpenApi {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private RegisterBrandService registerBrand;

	@Autowired
	private BrandModelAssembler brandModelAssembler;

	@Autowired
	private BrandInputDisassembler brandInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Brand> pagedResourcesAssembler;

	@CheckSecurity.Brands.CanConsult
	@Override
	@GetMapping
	public PagedModel<BrandModel> list(BrandFilter filter, Pageable pageable) {

		Pageable pageableTranslate = translatePageable(pageable);
		Page<Brand> brandsPage = null;

		if (filter.getName() != null || filter.getModelId() != null) {
			brandsPage = brandRepository.findAll(BrandSpecs.withFilter(filter), pageableTranslate);
		}

		else
			brandsPage = brandRepository.findAll(pageable);

		brandsPage = new PageWrapper<>(brandsPage, pageable);

		return pagedResourcesAssembler.toModel(brandsPage, brandModelAssembler);
	}

	@CheckSecurity.Brands.CanConsult
	@GetMapping("/noPagination")
	public CollectionModel<BrandModel> listNoPagination(BrandFilter filter) {

		List<Brand> allBrands;

		if (filter.getName() != null) {
			allBrands = brandRepository.findAll(BrandSpecs.withFilter(filter));
		} else
			allBrands = brandRepository.findAll();

		return brandModelAssembler.toCollectionModel(allBrands);
	}

	@CheckSecurity.Brands.CanConsult
	@Override
	@GetMapping("/noPagination/{modelId}")
	public CollectionModel<BrandModel> list(@PathVariable Long modelId) {

		List<Brand> allBrands;

		if (modelId != null) {
			allBrands = brandRepository.findAll(BrandSpecs.withIdModel(modelId));
		} else
			allBrands = brandRepository.findAll();

		return brandModelAssembler.toCollectionModel(allBrands);
	}

	@CheckSecurity.Brands.CanConsult
	@Override
	@GetMapping("/{brandId}")
	public BrandModel find(@PathVariable Long brandId) {
		Brand brand = registerBrand.findOrFail(brandId);

		return brandModelAssembler.toModel(brand);
	}

	@CheckSecurity.Brands.CanEdit
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BrandModel add(@RequestBody @Valid BrandInput brandInput) {
		try {
			Brand brand = brandInputDisassembler.toDomainObject(brandInput);

			brand = registerBrand.save(brand);

			BrandModel brandModel = brandModelAssembler.toModel(brand);

			ResourceUriHelper.addUriInResponseHeader(brandModel.getId());

			return brandModel;
		} catch (ModelNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Brands.CanEdit
	@Override
	@PutMapping("/{brandId}")
	public BrandModel update(@PathVariable Long brandId, @RequestBody @Valid BrandInput brandInput) {
		try {
			Brand currentBrand = registerBrand.findOrFail(brandId);

			brandInputDisassembler.copyToDomainObject(brandInput, currentBrand);

			currentBrand = registerBrand.save(currentBrand);

			return brandModelAssembler.toModel(currentBrand);
		} catch (ModelNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Brands.CanEdit
	@Override
	@DeleteMapping("/{brandId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long brandId) {

		Brand brand = registerBrand.findOrFail(brandId);
		String nameBrand = brand.getName();

		registerBrand.delete(brandId, nameBrand);
	}

	private Pageable translatePageable(Pageable apiPageable) {
		var mapping = Map.of("id", "code", "name", "name");

		return PageableTranslator.translate(apiPageable, mapping);
	}

}
