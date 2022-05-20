package com.hmd.typing.modules.module.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hmd.typing.modules.document.entity.API;
import com.hmd.typing.modules.module.dto.ModuleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter 
@Table(name = "Module")
@AllArgsConstructor
public class ModuleEntity extends API{
	
	public static ModuleEntity create(ModuleDTO dto) {
		ModuleEntity moduleEntity = new ModuleEntity();
		moduleEntity.name = dto.getName();
		moduleEntity.desc = dto.getDesc();
		return moduleEntity;
	}
	
	public ModuleDTO convertToDTO() {
		ModuleDTO dto = new ModuleDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setDesc(desc);
		return dto;
	}

	
	
	
	
	
	
	
	
	
}
