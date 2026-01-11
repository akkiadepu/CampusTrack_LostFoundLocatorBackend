package com.campusTrack.service;

import java.util.List;
import java.util.Optional;

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
	private LostFoundUsersRepository repository;
	
	private String User_id;
	private String role;
	private Users user;
	
	
	public void save(Users user2) {
		repository.save(user2);
	}
	
	
	public LostFoundUsersRepository getFoundUsersRepository() {
		return repository;
	}
	
	public String getUser_id() {
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
		 Users user = repository.findByUsername(username);

		    if (user == null) {
		        throw new UsernameNotFoundException("User not found");
		    }

		    // store user details for later use
		    this.user = user;
		    this.role = user.getRole();
		    this.User_id = user.getUser_id();

		    return org.springframework.security.core.userdetails.User
		            .withUsername(user.getUsername())
		            .password(user.getPassword())
		            .roles(user.getRole())
		            .build();
	}
	
	
	public List<Users> getAllStudents (){
		return repository.getAllStudents();
		
	}
	
	public void deleteUser(String id) {
		repository.deleteByUsername(id);
	}
	
	public boolean updateStudent(String username, String email, String password, String personalName) {
        int updatedRows = repository.updateStudentDetails(username, email, password, personalName);
        return updatedRows > 0;
    }
	
	public List<Users> getStudentsByUserName (String username){
		return repository.getStudentsByUserName(username);
		
	}
		

}
