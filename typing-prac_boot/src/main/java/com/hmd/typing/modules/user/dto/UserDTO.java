package com.hmd.typing.modules.user.dto;

import java.time.LocalDateTime;

import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	
	@Id
	private String email;
	private String password;
	private String grade;
	private Boolean isLeave;
	private LocalDateTime regDate;

}
