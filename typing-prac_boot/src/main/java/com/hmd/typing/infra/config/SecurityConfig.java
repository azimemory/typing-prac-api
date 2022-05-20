package com.hmd.typing.infra.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hmd.typing.infra.security.filter.JwtAuthenticationFilter;
import com.hmd.typing.infra.security.filter.JwtAuthorizationFilter;
import com.hmd.typing.infra.security.handler.JwtFailureHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security의 기본설정 대신 선언된 클래스에서 설정한 내용을 시큐리티에 적용
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtFailureHandler jwtFailureHandler;

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/static/**").requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable();

		http.httpBasic().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests()
			.mvcMatchers(HttpMethod.POST, "/login", "/api/join").permitAll()
			.mvcMatchers(HttpMethod.GET, "/api/module", "/api/package/*", "/api/class/*").permitAll()
			.anyRequest().authenticated();

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.addFilter(jwtAuthorizationFilter());
	}

	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		final JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager());
		filter.setAuthenticationFailureHandler(jwtFailureHandler);
		return filter;
	}

	@Bean
	JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
		final JwtAuthorizationFilter filter = new JwtAuthorizationFilter(authenticationManager());
		return filter;
	}

}
