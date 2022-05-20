package com.hmd.typing.modules.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.hmd.typing.infa.util.TestJWT;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired 
	MockMvc mockMvc;
	
	@Test
	public void join() throws Exception {
		mockMvc.perform(post("/api/join")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"azimemory@gmail.com\",\"password\":\"123qwe!@#\"}"))
		.andDo(print());
	}
	
	@Test
	public void login() throws Exception {
		mockMvc.perform(post("/login")
				.content("{\"email\":\"azimemory@gmail.com\",\"password\":\"123qwe!@#\"}"))
		.andDo(print());
	}
	
	@Test
	public void failLogin() throws Exception {
		mockMvc.perform(post("/login")
				.content("{\"email\":\"azimemory@gmail.com\",\"password\":\"1234\"}"))
		.andDo(print());
	}
	
	@Test
	public void mypage() throws Exception {
		mockMvc.perform(get("/api/mypage")
				.header("Authorization", TestJWT.NormalJwt))
		.andDo(print());
	}
	
	
	
	

}
