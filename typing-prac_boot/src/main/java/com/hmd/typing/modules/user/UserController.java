package com.hmd.typing.modules.user;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmd.typing.infra.api.RestApiController;
import com.hmd.typing.modules.user.validator.JoinForm;
import com.hmd.typing.modules.user.validator.JoinFormValidator;

@RestController
@RequestMapping(value = "/api")
public class UserController extends RestApiController{
	
	private UserService userService;
	private JoinFormValidator joinFormValidator;
	
	public UserController(ObjectMapper objectMapper, UserService userService, JoinFormValidator joinFormValidator) {
		super(objectMapper);
		this.userService = userService;
		this.joinFormValidator = joinFormValidator;
	}
	
	@InitBinder(value = "joinForm")
    public void joinFormValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(joinFormValidator);
    }
	
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody @Validated JoinForm form, Errors errors){
		
		if(errors.hasErrors()) {
			return createFailRestResponse(Map.of("msg", errors.getFieldError().getDefaultMessage()));
		}
		
		userService.registerUser(form);
		return createRestResponse(Map.of("data", form));
	}

	@GetMapping("/mypage")
	public String mypage(@AuthenticationPrincipal String email) {
		
		return email;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
