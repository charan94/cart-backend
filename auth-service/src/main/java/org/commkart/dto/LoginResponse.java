package org.commkart.dto;

public class LoginResponse {

	public LoginResponse(String token, String redirectUrl) {
		this.token = token;
		this.redirectUrl = redirectUrl;
	}

	private String token;
	
	private String redirectUrl;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	
}
