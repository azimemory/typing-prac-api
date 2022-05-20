package com.hmd.typing.infra.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmd.typing.infra.api.RestApiController;

import lombok.extern.slf4j.Slf4j;

@Component
@RestControllerAdvice(basePackages = "com.hmd.typing")
@Slf4j
public class ExceptionAdvice extends RestApiController{
	
	public ExceptionAdvice(ObjectMapper objectMapper) {
		super(objectMapper);
		// TODO Auto-generated constructor stub
	}

	//예외가 발생했음으로 응답상태코드를 500번으로 지정, default 200
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HandlableException.class)
	public String handlableExceptionProcess(HandlableException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionProcess(DataAccessException e) {
		log.error(e.getMessage());
		return e.getMessage();
	}
	
	
	
	

}
