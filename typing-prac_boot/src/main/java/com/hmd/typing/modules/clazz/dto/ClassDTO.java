package com.hmd.typing.modules.clazz.dto;

import com.hmd.typing.modules.packaze.dto.PackageDTO;

import lombok.Data;

@Data
public class ClassDTO {
	
	private Long id;
	private String name;
	private String desc;
	private PackageDTO packaze;

}
