package com.campusTrack.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Table(name = "User")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int User_id;

	@Column(name = "userName", nullable = false)
	private String name;

	@Column(name = "userEmail", nullable = false)
	private String email;

	@Column(name = "userPassword", nullable = false)
	private String password;

	@Column(name = "userRole", nullable = false)
	private String role;

	@Column(name = "userVerification", nullable = false)
	private boolean verified;

	@Column(name = "created_At", nullable = false)
	private LocalDateTime created_At;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int users_id, String name, String email, String password, String role, boolean verified,
			LocalDateTime created_At) {
		super();
		User_id = users_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.verified = verified;
		this.created_At = created_At;
	}

	public Users(int users_id, String name, String email, String password, String role, boolean verified) {
		super();
		User_id = users_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.verified = verified;
	}

	public int getUsers_id() {
		return User_id;
	}

	public void setUsers_id(int users_id) {
		User_id = users_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public LocalDateTime getCreated_At() {
		return created_At;
	}

	public void setCreated_At(LocalDateTime created_At) {
		this.created_At = created_At;
	}

	@Override
	public String toString() {
		return "Users [Users_id=" + User_id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", role=" + role + ", verified=" + verified + ", created_At=" + created_At + "]";
	}

}
