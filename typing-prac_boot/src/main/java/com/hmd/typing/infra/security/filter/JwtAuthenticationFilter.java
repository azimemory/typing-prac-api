package com.hmd.typing.infra.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmd.typing.infra.properties.AuthToken;
import com.hmd.typing.infra.util.jwt.JwtUtil;
import com.hmd.typing.modules.user.dto.UserDTO;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher("/login"), authenticationManager);
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		UserDTO credentials = null;
		
		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				credentials.getEmail(), credentials.getPassword(), new ArrayList<>());

		Authentication auth = getAuthenticationManager().authenticate(authenticationToken);
		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetails principal = (UserDetails) authResult.getPrincipal();
		
		List<String> authorities = principal.getAuthorities()
							.stream()
							.map(e -> e.getAuthority())
							.collect(Collectors.toList());
		
		String token = JwtUtil.generateToken("accessToken", principal.getUsername(), authorities);
		response.addHeader(AuthToken.JWT.HEADER_STRING, AuthToken.JWT.TOKEN_PREFIX + token);
	}
	
	
}
