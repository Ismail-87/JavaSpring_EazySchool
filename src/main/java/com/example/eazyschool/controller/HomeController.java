package com.example.eazyschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
     /*  
    @GetMapping({"/", "/home"})
    public String displayHomePage(@RequestParam (value ="username", defaultValue="Santana", required=true) String username, Model model) {
        model.addAttribute("username", username.toUpperCase());
        name= username;
        return "home";
    }*/
    
    @RequestMapping({"/","/home",""})
    public String displayUserDetails() {
    	    	
    	return "home";
    	
    }


}

	
