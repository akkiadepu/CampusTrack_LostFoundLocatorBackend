package com.campusTrack.configer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.campusTrack.service.LostFoundUsersService;

@Configuration
@EnableMethodSecurity
public class SystemConfig {
	@Autowired
	private EncoderConfig encoderConfig;

	@Autowired
	private LostFoundUsersService service;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//				.authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.GET, "/lostfound/**")
//						.permitAll().requestMatchers("/lostfound/**").permitAll().anyRequest().authenticated());
//		return http.build();
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .cors(Customizer.withDefaults())
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
//	            .requestMatchers("/lostfound/login/**").permitAll()
	        	.requestMatchers("/lostfound/login/**", "/lostfound/register/**").permitAll()
	            .requestMatchers("/lostfound/logout").permitAll()
	            .requestMatchers("/lostfound/student/**").permitAll()
	            .requestMatchers("/lostfound/ws/**").permitAll()
	            .requestMatchers("/lostfound/*").permitAll()
	            .requestMatchers("/lostfound/**").permitAll()
	            .requestMatchers("/lostfound/match/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	                .loginProcessingUrl("/lostfound/login")
	                .permitAll()
	            )
	        .logout(logout -> logout
	            .logoutUrl("/lostfound/logout")
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	            .logoutSuccessHandler((request, response, authentication) -> {
	                response.setStatus(200);
	                response.getWriter().write("Logout success");
	            })
	        );

	    return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowedOrigins(List.of(
	    	    "http://localhost:3000",
	    	    "http://localhost:5173"
	    	));
//	    config.setAllowedOrigins(List.of("https://campus-track-my-lost-found-frontend.vercel.app"));
	    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    config.setAllowedHeaders(List.of("*"));
	    config.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);
	    return source;
	}

}
