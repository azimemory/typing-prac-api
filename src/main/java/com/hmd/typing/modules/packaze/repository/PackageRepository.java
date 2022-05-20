package com.hmd.typing.modules.packaze.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hmd.typing.modules.module.entity.ModuleEntity;
import com.hmd.typing.modules.packaze.entity.PackageEntity;

public interface PackageRepository extends JpaRepository<PackageEntity, Long>{
	
	List<PackageEntity> findByModule(ModuleEntity module);
	PackageEntity findByName(String name);	
	
}