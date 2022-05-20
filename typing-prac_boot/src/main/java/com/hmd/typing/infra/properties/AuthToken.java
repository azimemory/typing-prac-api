package com.hmd.typing.infra.properties;

public enum AuthToken {
	
	JWT("azimemory", 2 * 60 * 60, "Bearer ","Authorization");
	
	public final String SECRET;
	public final int EXPIRATION_TIME; 
	public final String TOKEN_PREFIX;
	public final String HEADER_STRING;
    
	private AuthToken(String secret, int expirationTime, String tokenPrefix, String headerString) {
		SECRET = secret;
		EXPIRATION_TIME = expirationTime;
		TOKEN_PREFIX = tokenPrefix;
		HEADER_STRING = headerString;
	}
    
	
    
    
    

}
