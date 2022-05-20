package com.hmd.typing.modules.clazz.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hmd.typing.modules.clazz.dto.ClassDTO;
import com.hmd.typing.modules.document.entity.API;
import com.hmd.typing.modules.packaze.entity.PackageEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter 
@Table(name = "Class")
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity extends API{
	
	@ManyToOne
	PackageEntity packaze;
	
	public static ClassEntity create(ClassDTO dto, PackageEntity packageEntity) {
		ClassEntity entity = new ClassEntity();
		entity.name = dto.getName();
		entity.desc = dto.getDesc();
		entity.packaze = packageEntity;
		return entity;
	}
	
	public ClassDTO convertToDTO() {
		ClassDTO dto = new ClassDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setDesc(desc);
		dto.setPackaze(packaze.convertToDTO());
		return dto;
	}
	
}
