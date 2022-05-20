package com.hmd.typing.infa.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Jwts;

@SpringBootTest
public class JwtTokenTest {
	
	private final String SECRETE = "azimemory";
	
	@Test
	@DisplayName("정상 jwt토큰 파싱 테스트")
	public void parseToken() {
		System.out.println(Jwts.parser().setSigningKey(SECRETE).parseClaimsJws(TestJWT.NormalJwt.str.split(" ")[1]).getBody());
	}
	
	@Test
	@DisplayName("위조된 jwt토큰 파싱 테스트")
	public void parseForgeriedToken() {
		//System.out.println(Jwts.parser().setSigningKey(SECRETE).parseClaimsJws(TestJWT.ForgeriedJwt.str.split(" ")[1]).getBody());
	}
}
