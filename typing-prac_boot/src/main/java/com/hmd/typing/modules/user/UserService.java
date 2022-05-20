package com.hmd.typing.modules.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hmd.typing.infra.code.Grade;
import com.hmd.typing.modules.user.entity.UserEntity;
import com.hmd.typing.modules.user.validator.JoinForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService{
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmailAndIsLeave(username, false);
		if(userEntity == null) throw new UsernameNotFoundException(username);
		return new UserAccount(userEntity.convertToDTO());
	}

	@Transactional
	public void registerUser(JoinForm form) {
		form.setGrade(Grade.USER.role);
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		UserEntity userEntity = UserEntity.register(form);
		userRepository.save(userEntity);
	}
}













