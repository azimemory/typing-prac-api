package com.hmd.typing.modules.clazz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hmd.typing.modules.clazz.entity.ClassEntity;
import com.hmd.typing.modules.packaze.entity.PackageEntity;

public interface ClassRepository extends JpaRepository<ClassEntity, Long>{

	List<ClassEntity> findByPackaze(PackageEntity packaze);

}
