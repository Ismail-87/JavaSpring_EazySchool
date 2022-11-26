package com.example.eazyschool.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.eazyschool.model.Person;
import com.example.eazyschool.repository.PersonRepository;

@Controller
public class DashboardController {
	
	@Autowired
	PersonRepository personRepository;
	
	@RequestMapping(value="/dashboard")
	public String displayDashboard(Model model, Authentication authentication, HttpSession session) throws Exception {
		Person person = personRepository.readByEmail(authentication.getName());
		model.addAttribute("username", person.getName());
		model.addAttribute("roles",authentication.getAuthorities().toString());
		//throw new Exception ("It is a bad moment. Please come back later");
		if(null != person.getEazyClass() && null != person.getEazyClass().getName()){
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }
		session.setAttribute("loggedInPerson", person);
		return "dashboard.html";
	}

}
