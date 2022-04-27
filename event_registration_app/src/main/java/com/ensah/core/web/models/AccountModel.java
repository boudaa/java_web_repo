package com.ensah.core.web.models;


public class AccountModel {
	
	private String username;
	
	private String password;
	
	private Long roleId;
	
	private Long personId;
	
	
	public AccountModel() {
	}

	public AccountModel(Long personId) {
		this.personId = personId;
	}

	
	public AccountModel(Long roleId, Long personId) {
		this.roleId = roleId;
		this.personId = personId;
	}

	public AccountModel(String username, String password, Long roleId, Long personId) {
		this.username = username;
		this.password = password;
		this.roleId = roleId;
		this.personId = personId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	
	
	
}
