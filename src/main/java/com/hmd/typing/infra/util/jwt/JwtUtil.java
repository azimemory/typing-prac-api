package com.hmd.typing.infra.util.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil implements Serializable {
	
	private static final long serialVersionUID = -2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 2 * 60 * 60;
	private static String SECRETE = "azimemory";
	
	public static String generateToken(String subject, String audience, List<String> authorities) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("authority", authorities);
		return doGenerateToken(subject, audience, claims);
	}

	public static Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public static String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getAudience);
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorityFromToken(String token) {
		return (List<String>)getAllClaimsFromToken(token).get("authority");
	}
	
	private static Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SECRETE).parseClaimsJws(token).getBody();
	}
	
	private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private static Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	private static String doGenerateToken(String subject, String audience, Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setAudience(audience)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 100000000))
				.signWith(SignatureAlgorithm.HS512, SECRETE).compact();
	}
	
}