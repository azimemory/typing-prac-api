package com.hmd.typing.modules.clazz;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.hmd.typing.infa.util.TestJWT;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassControllerTest {
	
	@Autowired 
	MockMvc mockMvc;
	
	@Test
	public void classes() throws Exception {
		mockMvc.perform(get("/api/class/java.lang")
				.header("Authorization", TestJWT.NormalJwt.str))
		.andDo(print());
	}
	
	
	
	

}
