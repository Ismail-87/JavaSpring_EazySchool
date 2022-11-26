package com.example.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	
	//adding login logic in config
	@Bean
	SecurityFilterChain defaultSecurityFilterChain( HttpSecurity http) throws Exception{
		
		http.csrf().ignoringAntMatchers("/saveMsg")
		.ignoringAntMatchers("/public/**")
		.ignoringAntMatchers("/api/**").and()
		.authorizeRequests()
		.mvcMatchers("/home").permitAll()
		.mvcMatchers("/dashboard").authenticated()
		.mvcMatchers("/displayProfile").authenticated()
		.mvcMatchers("/updateProfile").authenticated()
		.mvcMatchers("/displayMessages").hasRole("ADMIN")
		.mvcMatchers("/admin/**").hasRole("ADMIN")
		.mvcMatchers("/student/**").hasRole("STUDENT")
		.mvcMatchers("/about").permitAll()
		.mvcMatchers("/contact").permitAll()
		.mvcMatchers("/holidays/**").permitAll()
		.mvcMatchers("/saveMsg").permitAll()
		.mvcMatchers("/courses").permitAll()
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
		.and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
		.and().httpBasic();
		
		
	return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * @Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails admin= User.withDefaultPasswordEncoder()
				.username("Ismail")
				.password("Ismail")
				.roles("ADMIN")
				.build();
		UserDetails user= User.withDefaultPasswordEncoder()
				.username("Santana")
				.password("Santana")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager (admin,user);
	}*/
	
	/*
	@Bean
	SecurityFilterChain defaultSecurityFilterChain( HttpSecurity http) throws Exception{
		
		http.authorizeRequests().anyRequest().permitAll()
		.and().formLogin().and().httpBasic();
		
		return http.build();
	}*/
	
	/*
	@Bean
	SecurityFilterChain defaultSecurityFilterChain( HttpSecurity http) throws Exception{
		
		http.authorizeRequests().anyRequest().denyAll()
		.and().formLogin().and().httpBasic();
		
		return http.build();
	}
	*/


}
