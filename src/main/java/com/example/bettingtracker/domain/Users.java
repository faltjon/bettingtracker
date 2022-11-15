package com.example.bettingtracker.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Users {

	// Generate ID for BetData entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userID", nullable = false, updatable = false)
	private Long userID;

	// Only allow unique usernames
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;
	// Only allow unique email
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "role", nullable = false)
	private String role;

	public List<Deposit> getDeposit() {
		return deposit;
	}

	public void setDeposit(List<Deposit> deposit) {
		this.deposit = deposit;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
	private List<Deposit> deposit;

	public Users() {

	}

	public Users(String username, String password, String email, String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [userID=" + userID + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", role=" + role + "]";
	}

}