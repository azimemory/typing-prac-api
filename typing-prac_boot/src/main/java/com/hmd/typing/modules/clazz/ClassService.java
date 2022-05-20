package com.hmd.typing.modules.clazz;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hmd.typing.modules.clazz.dto.ClassDTO;
import com.hmd.typing.modules.clazz.repository.ClassRepository;
import com.hmd.typing.modules.packaze.entity.PackageEntity;
import com.hmd.typing.modules.packaze.repository.PackageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassService {
	
	private final ClassRepository classRepository;
	private final PackageRepository packageRepository;
	
	public List<ClassDTO> findByPackage(String packageName) {
		PackageEntity packaze = packageRepository.findByName(packageName);
		List<ClassDTO> classList = classRepository.findByPackaze(packaze)
										.stream().map(e -> e.convertToDTO()).collect(Collectors.toList());
		return classList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
