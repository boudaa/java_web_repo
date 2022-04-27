package com.ensah.core.bo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonne;

	private String firstName;

	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private List<UserAccount> accounts;

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<UserAccount> accounts) {
		this.accounts = accounts;
	}

}
