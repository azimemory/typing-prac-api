package com.hmd.typing.modules.clazz.controller;

import java.util.List;import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmd.typing.infra.api.RestApiController;
import com.hmd.typing.modules.clazz.ClassService;
import com.hmd.typing.modules.clazz.dto.ClassDTO;

@RestController
@RequestMapping(value = "/api/class", produces = "application/json; charset=utf-8")
public class ClassController extends RestApiController{
	
	private ClassService classService;

	public ClassController(ObjectMapper objectMapper, ClassService classService) {
		super(objectMapper);
		this.classService = classService;
	}
	
	@GetMapping("{packageName}")
	public ResponseEntity<String> getClass(@PathVariable String packageName){
		List<ClassDTO> classList = classService.findByPackage(packageName);
		return createRestResponse(Map.of("classList", classList));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
