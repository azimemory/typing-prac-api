package com.hmd.typing.infra.code;

public enum Grade {
	
	USER("ROLE_USER","일반사용자"),
	ADMIN("ROLE_ADMIN","관리자");
	
	public final String role;
	public final String desc;
	
	private Grade(String role, String desc) {
		this.role = role;
		this.desc = desc;
	}
	
	
}
