package com.hmd.typing.modules.module.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmd.typing.infra.api.RestApiController;
import com.hmd.typing.modules.module.ModuleService;
import com.hmd.typing.modules.module.dto.ModuleDTO;

@RestController
@RequestMapping(value = "/api", produces = "application/json; charset=utf-8")
public class ModuleController extends RestApiController{
	
	private ModuleService moduleService;
	
	public ModuleController(ObjectMapper objectMapper, ModuleService moduleService) {
		super(objectMapper);
		this.moduleService = moduleService;
	}
	
	@GetMapping("module")
	public ResponseEntity<String> overView(){
		List<ModuleDTO> moduleList = moduleService.getAllModule();
		return createRestResponse(Map.of("moduleList", moduleList));
	}
	
	@GetMapping("module/{moduleName}")
	public ResponseEntity<String> getModule(@PathVariable String moduleName){
		ModuleDTO module = moduleService.getModule(moduleName);
		return createRestResponse(Map.of("module", module));
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
