package com.hmd.typing.infa.util;

public enum TestJWT {
	
	//2만시간짜리 jwt
	NormalJwt("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhemltZW1vcnlAZ21haWwuY29tIiwiYXV0aG9yaXR5IjpbIlJPTEVfVVNFUiJdLCJleHAiOjE3MjQzMzUyMjUsImlhdCI6MTY1MjMzNTIyNX0.5McqDO2h9kRp-u9TlKfh7nsbS9JhAhTCNKpqgpBxKMFetDrUhQv2jBvQ0z0Dhav6HE1tFPf5_CNEUxL6EP3slA"),
	ForgeriedJwt("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhemltZW1vcnlAZ21haWwuY29tIiwiYXV0aG9yaXR5IjpbIlJPTEVfVVNFUiJdLCJleHAiOjE3MjQzMzUyMjUsImlhdCI6MTY1MjMzNTIyNX0.5McqDO2h9kRp-u9TlKfh7nsbS9JhAhTCNKpqgpBxKMFetDrUhQv2jBvQ0z0Dhav6HE1tFPf5_CNEUxL6EP3slA");
	
	public String str;

	private TestJWT(String str) {
		this.str = str;
	}
	
	
}
