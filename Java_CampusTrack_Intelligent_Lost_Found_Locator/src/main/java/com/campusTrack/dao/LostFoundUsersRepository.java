package com.campusTrack.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusTrack.entity.Users;

@Repository
public interface LostFoundUsersRepository extends JpaRepository<Users, String> {
	
	  Users findByUsername(String username);

}
