package br.com.alura.clientelo.config.security;

public class TokenDto {
	
	private String token;
	
	private String authType;
	
	private String message;

	public TokenDto(String token, String authType) {
		this.token = token;
		this.authType = authType;
		this.message = "OK";
	}
	
	public TokenDto(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public String getAuthType() {
		return authType;
	}

	public String getMessage() {
		return message;
	}

}
