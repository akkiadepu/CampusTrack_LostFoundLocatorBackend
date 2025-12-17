package com.campusTrack.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.campusTrack.entity.FoundItem;
import com.campusTrack.entity.Users;

import jakarta.transaction.Transactional;

@Repository
public interface LostFoundUsersRepository extends JpaRepository<Users, String> {

	Users findByUsername(String username);

	@Query("select a from Users a where a.role = 'Student'")
	public List<Users> getAllStudents();

	@Modifying
	@Transactional
	@Query("""
			    UPDATE Users u
			    SET u.email = :email,
			        u.password = :password,
			        u.personalName = :personalName
			    WHERE u.username = :username
			      AND u.role = 'Student'
			""")
	int updateStudentDetails(@Param("username") String username, @Param("email") String email,
			@Param("password") String password, @Param("personalName") String personalName);

	
	@Modifying
	@Transactional
	@Query("DELETE FROM Users u WHERE u.username = :username")
	void deleteByUsername(@Param("username") String username);

	@Query("select a from Users a where a.role = 'Student' and a.username=?1")
	public List<Users> getStudentsByUserName(String username);

}
