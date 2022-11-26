package com.example.eazyschool.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String displayLoginPage(@RequestParam(value="error", required=false) String error, 
									@RequestParam(value="logout", required=false) String logout,
									@RequestParam(value="register", required=false) String register,
									Model model) {
		
		String errorMessage=null;
		String successMessage=null;
		if (error!=null) {
			errorMessage ="User Name or Password is invalid";
		}
		if(logout!=null) {
			successMessage= "You have been successfully logged out";
		}
		if(register!=null) {
			successMessage= "Your have successfully registered";
		}
		model.addAttribute("errorMessage", errorMessage);
		//redirAttrs.addFlashAttribute("logoutMessage", logoutMessage);
		model.addAttribute("successMessage", successMessage);
		
		return "login";
		
	}
	
	@RequestMapping(value="/logout",method= {RequestMethod.GET})
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth!=null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}

}
