package com.isika.al5.projet.service.dto;

import java.util.List;

import com.isika.al5.projet.service.entity.Role;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
	private String username;
	private String token;
	private Role role; 
	
	
	
	public TokenResponse() {
		
	}
	public TokenResponse(String username, String token) {

		this.username = username;
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	
	
	
	
	

}
