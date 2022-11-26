package com.example.eazyschool.controller;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.FormSubmitEvent.MethodType;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.eazyschool.model.Address;
import com.example.eazyschool.model.Person;
import com.example.eazyschool.model.Profile;
import com.example.eazyschool.repository.PersonRepository;

@Controller
public class ProfileController {
	@Autowired
	PersonRepository personRepository;
	
	@RequestMapping(value="/displayProfile")
	public ModelAndView displayProfile(Model model, HttpSession session) {
		Person person = (Person) session.getAttribute("loggedInPerson");
		Profile profile = new Profile();
		profile.setName(person.getName());
		profile.setMobileNumber(person.getMobileNumber());
		profile.setEmail(person.getEmail());
		if(person.getAddress()!=null && person.getAddress().getAddressId()>0) {
			profile.setAddress1(person.getAddress().getAddress1());
			profile.setAddress2(person.getAddress().getAddress2());
			profile.setCity(person.getAddress().getCity());
			profile.setState(person.getAddress().getState());
			profile.setZipCode(person.getAddress().getZipCode());
		}
		ModelAndView modelAndView = new ModelAndView("profile.html");
		modelAndView.addObject("profile", profile);
		return modelAndView;
		
	}
	
	@RequestMapping(value="/updateProfile", method = {RequestMethod.POST})
	public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession session, RedirectAttributes redirAttrs) {
		
		{
	        if(errors.hasErrors()){
	            return "profile.html";
	        }
	        Person person = (Person) session.getAttribute("loggedInPerson");
	        person.setName(profile.getName());
	        if(!person.getEmail().equalsIgnoreCase(profile.getEmail())) {
	        	redirAttrs.addFlashAttribute("emailUpdateMsg", "Looks like you have updated email, please logout and login back!");
	        }
	        person.setEmail(profile.getEmail());
	        person.setMobileNumber(profile.getMobileNumber());
	        if(person.getAddress() ==null || !(person.getAddress().getAddressId()>0)){
	            person.setAddress(new Address());
	        }
	        person.getAddress().setAddress1(profile.getAddress1());
	        person.getAddress().setAddress2(profile.getAddress2());
	        person.getAddress().setCity(profile.getCity());
	        person.getAddress().setState(profile.getState());
	        person.getAddress().setZipCode(profile.getZipCode());
	        Person savedPerson = personRepository.save(person);
	        session.setAttribute("loggedInPerson", savedPerson);
	        
	        redirAttrs.addFlashAttribute("successMessage", "Your personal details updated successfully!");
	        return "redirect:/displayProfile";
	    }
	}
	

}
