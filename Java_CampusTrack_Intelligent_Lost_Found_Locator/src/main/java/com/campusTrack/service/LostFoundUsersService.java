package com.campusTrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.campusTrack.dao.LostFoundUsersRepository;
import com.campusTrack.entity.Users;

@Service
public class LostFoundUsersService implements UserDetailsService {
	
	@Autowired
	private LostFoundUsersRepository Repository;
	
	private int User_id;
	private String role;
	private Users user;
	
	
	public void save(Users user2) {
		Repository.save(user2);
	}
	
	
	public LostFoundUsersRepository getFoundUsersRepository() {
		return Repository;
	}
	public int getUser_id() {
		return User_id;
	}
	public String getRole() {
		return role;
	}
	public Users getUser() {
		return user;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	

}
