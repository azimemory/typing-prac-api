package com.hmd.typing.modules.module;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hmd.typing.modules.module.dto.ModuleDTO;
import com.hmd.typing.modules.module.repository.ModuleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModuleService {
	
	private final ModuleRepository moduleRepository;

	public List<ModuleDTO> getAllModule() {
		List<ModuleDTO> moduleList = moduleRepository.findAll()
									.stream().map(e -> e.convertToDTO())
									.collect(Collectors.toList());
		return moduleList;
	}
	
	public ModuleDTO getModule(String moduleName) {
		ModuleDTO module = moduleRepository.findByName(moduleName).convertToDTO();
		return module;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
