package com.hmd.typing.modules.packaze;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hmd.typing.modules.module.entity.ModuleEntity;
import com.hmd.typing.modules.module.repository.ModuleRepository;
import com.hmd.typing.modules.packaze.dto.PackageDTO;
import com.hmd.typing.modules.packaze.repository.PackageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PackageService {
	
	private final PackageRepository packageRepository;
	private final ModuleRepository moduleRepository;
	
	public List<PackageDTO> getPackage(String moduleName) {
		ModuleEntity module = moduleRepository.findByName(moduleName);
		List<PackageDTO> packageList = packageRepository.findByModule(module)
									.stream().map(e -> e.convertToDTO())
									.collect(Collectors.toList());
		return packageList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
