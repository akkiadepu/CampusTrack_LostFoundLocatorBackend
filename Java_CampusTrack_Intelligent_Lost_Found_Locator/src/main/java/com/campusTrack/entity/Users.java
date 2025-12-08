package com.campusTrack.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
//@Table(name = "User")
public class Users extends User {

	@Id
	 @GeneratedValue(strategy = GenerationType.UUID)
	private String User_id;

	private String username;

	private String email;

	private String password;

	private String role;

	private String personalName;

	public Users(String name, String password, Collection<? extends GrantedAuthority> authorities, String name2,
			String personalname2, String email2, String password2, String role2, String personalName2) {
		super(name, password, authorities);

		this.username = name2;
		this.email = email2;
		this.password = password2;
		this.role = role2;
		this.personalName = personalName2;
	}

	public Users() {
	    super("default", "default", new ArrayList<>());
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public String getUser_id() {
		return User_id;
	}

	public void setUser_id(String user_id) {
		User_id = user_id;
	}

}
