package com.hmd.typing.modules.user.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.hmd.typing.modules.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JoinFormValidator implements Validator{
	
	private final UserRepository userRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return JoinForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		JoinForm form = (JoinForm) target;
		if(userRepository.existsById(form.getEmail())) {
			errors.rejectValue("email", "error-email", "사용 중인 이메일 입니다.");
		}
	}
	
	
	
	
	
	
	
	
	
	

}
