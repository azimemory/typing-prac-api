package com.hmd.typing.modules.packaze.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hmd.typing.modules.document.entity.API;
import com.hmd.typing.modules.module.entity.ModuleEntity;
import com.hmd.typing.modules.packaze.dto.PackageDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter 
@Table(name = "Package")
@AllArgsConstructor
@NoArgsConstructor
public class PackageEntity extends API{
	
	@ManyToOne
	private ModuleEntity module = new ModuleEntity();
	
	public static PackageEntity create(PackageDTO dto, ModuleEntity module) {
		PackageEntity entity = new PackageEntity();
		entity.name = dto.getName();
		entity.desc = dto.getDesc();
		entity.module = module;
		return entity;
	}
	
	public PackageDTO convertToDTO() {
		PackageDTO dto = new PackageDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setDesc(desc);
		if(module != null) dto.setModule(module.convertToDTO());
		return dto;
	}
	
	

	
	
	
	
	
	
	
	
	
	
}
