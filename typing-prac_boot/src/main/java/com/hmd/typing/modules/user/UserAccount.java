package com.hmd.typing.modules.user;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.hmd.typing.modules.user.dto.UserDTO;

@SuppressWarnings("serial")
public class UserAccount extends User{

	public UserAccount(UserDTO user) {
		super(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getGrade())));
	}
	
	
	

}
