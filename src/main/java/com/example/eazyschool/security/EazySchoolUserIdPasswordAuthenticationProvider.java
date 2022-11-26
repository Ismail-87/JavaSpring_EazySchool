package com.example.eazyschool.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.eazyschool.model.Person;
import com.example.eazyschool.model.Roles;
import com.example.eazyschool.repository.PersonRepository;

@Component
public class EazySchoolUserIdPasswordAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	PersonRepository personRepository; 
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	//validating the user credential against DB
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String pwd =authentication.getCredentials().toString();
		Person person = personRepository.readByEmail(email);
		if(person!=null && person.getPersonId()>0 && passwordEncoder.matches(pwd, person.getPwd())) {
			return new UsernamePasswordAuthenticationToken(email,null,getGrantedAuthorities(person.getRoles()));
		}else {	
				
		 throw new BadCredentialsException("Invalid credentials!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Roles roles){
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles.getRoleName()));
		return grantedAuthorities;
		
	}

}
