package com.hmd.typing.modules.user.validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class JoinForm {
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}")
	private String password;
	
	private String grade;

}
