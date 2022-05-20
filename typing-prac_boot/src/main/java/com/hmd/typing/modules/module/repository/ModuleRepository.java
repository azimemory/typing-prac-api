package com.hmd.typing.modules.module.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hmd.typing.modules.module.entity.ModuleEntity;

public interface ModuleRepository extends JpaRepository<ModuleEntity, Long>{
	
	ModuleEntity findByName(String name);
	
}
