package com.hmd.typing.modules.packaze.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmd.typing.infra.api.RestApiController;
import com.hmd.typing.modules.packaze.PackageService;
import com.hmd.typing.modules.packaze.dto.PackageDTO;

@RestController
@RequestMapping(value = "/api/package", produces = "application/json; charset=utf-8")
public class PackageController extends RestApiController{
	
	private PackageService packageService;
	
	public PackageController(ObjectMapper objectMapper, PackageService packageService) {
		super(objectMapper);
		this.packageService = packageService;
	}

	@GetMapping("{moduleName}")
	public ResponseEntity<String> getPackageInModule(@PathVariable String moduleName){
		List<PackageDTO> packageList = packageService.getPackage(moduleName);
		return createRestResponse(Map.of("packageList",packageList));
	}
	
	
	
	
	
	
	
	
	
	
}
