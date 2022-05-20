package com.hmd.typing.modules.packaze.dto;

import com.hmd.typing.modules.module.dto.ModuleDTO;

import lombok.Data;

@Data
public class PackageDTO {
	
	private Long id;
	private String name;
	private String desc;
	private ModuleDTO module;
}
