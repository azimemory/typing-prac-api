package com.hmd.typing.modules.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.hmd.typing.modules.user.dto.UserDTO;
import com.hmd.typing.modules.user.validator.JoinForm;

import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Table(name = "MEMBER")
@DynamicInsert
@DynamicUpdate
public class UserEntity{
	
	@Id
	private String email;
	private String password;
	private String grade;
	
	@Column(columnDefinition = "int default 0")
	private Boolean isLeave;
	
	@Column(columnDefinition = "date default sysdate")
	private LocalDateTime regDate;
	
	public UserDTO convertToDTO() {
		UserDTO user = new UserDTO();
		user.setEmail(email);
		user.setPassword(password);
		user.setGrade(grade);
		user.setIsLeave(isLeave);
		user.setRegDate(regDate);
		return user;
	}
	
	public static UserEntity register(JoinForm form) {
		UserEntity userEntity = new UserEntity();
		userEntity.email = form.getEmail();
		userEntity.password = form.getPassword();
		userEntity.grade = form.getGrade();
		return userEntity;
	}
	
	
	
	
	
	
	
	
	
	
	

}
