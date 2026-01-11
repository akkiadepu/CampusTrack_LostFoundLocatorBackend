package com.campusTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.campusTrack.configer.EncoderConfig;
import com.campusTrack.entity.Users;
import com.campusTrack.service.LostFoundUsersService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/lostfound")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoginController {

	@Autowired
	private LostFoundUsersService service;

	@Autowired
	private EncoderConfig config;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public void registerNewUser(@RequestBody Users user) {
		PasswordEncoder bCrypt = config.passwordEncoding();
		String encodedPassword = bCrypt.encode(user.getPassword());
		user.setPassword(encodedPassword);
		service.save(user);
	}

	@GetMapping("/login/{userId}/{password}")
	public String validateUser(@PathVariable String userId, @PathVariable String password) {
		String role = "false";
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
			role = service.getRole();
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception ex) {
		}
		return role;
	}

	@GetMapping("/login")
	public Users getUserDetails() {
		return service.getUser();
	}

	@GetMapping("/user")
	public String getUserId() {
		return service.getUser_id();
	}

	@GetMapping("/role")
	public String getRole() {
		return service.getRole();
	}
	

	@GetMapping("/student")
	public List<Users> getAllStudents() {
		return service.getAllStudents();
	}

	@GetMapping("/student/{username}")
	public List<Users> getStudentsByUserName(@PathVariable String username) {
		return service.getStudentsByUserName(username);

	}

	@PutMapping("/student/{username}")
	public ResponseEntity<String> updateStudent(@PathVariable String username, @RequestBody Users user) {

		boolean updated = service.updateStudent(username, user.getEmail(), user.getPassword(), user.getPersonalName());

		return updated ? ResponseEntity.ok("Student details updated successfully") : ResponseEntity.notFound().build();
	}
	

	@DeleteMapping("/login/{username}")
	public void deleteUser(@PathVariable String username) {
		service.deleteUser(username);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {

		SecurityContextHolder.clearContext();

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		return ResponseEntity.ok("Logout successful");
	}

}
