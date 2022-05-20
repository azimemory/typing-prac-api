package com.hmd.typing.infra.security.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.hmd.typing.infra.code.ErrorCode;
import com.hmd.typing.infra.exception.HandlableException;
import com.hmd.typing.infra.properties.AuthToken;
import com.hmd.typing.infra.util.jwt.JwtUtil;

import io.jsonwebtoken.SignatureException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
       
        String header = request.getHeader(AuthToken.JWT.HEADER_STRING);

        if(header == null || !header.startsWith(AuthToken.JWT.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        
    	try {
    		
    		String token = request.getHeader(AuthToken.JWT.HEADER_STRING).split(" ")[1];
    		
        	if(token == null || JwtUtil.isTokenExpired(token)) throw new HandlableException(ErrorCode.JWT_VERIFICATION_FAIL);
        	
            String email = JwtUtil.getUsernameFromToken(token);
            List<SimpleGrantedAuthority> authority = JwtUtil.getAuthorityFromToken(token)
            		.stream()
            		.map(e -> new SimpleGrantedAuthority(e))
            		.collect(Collectors.toList());
        	
            UsernamePasswordAuthenticationToken auth 
            	= new UsernamePasswordAuthenticationToken(email, null, authority);
            
            return auth;
            
    	}catch(SignatureException e) {
    		throw new HandlableException(ErrorCode.JWT_VERIFICATION_FAIL);
    	}
    }
}
