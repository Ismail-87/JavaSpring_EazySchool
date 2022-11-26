package com.example.eazyschool.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.eazyschool.model.Contact;
import com.example.eazyschool.service.ContactService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ContactController {
	
		
	private ContactService contactService;
	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
		
	}
	
	@RequestMapping("/contact")
	public String displayContact(Model model){
		model.addAttribute("contact", new Contact());
		return "contact";
		
	}
	
	@PostMapping("/saveMsg")
	public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors, RedirectAttributes redirAttrs){
		
		if(errors.hasErrors()) {
			log.error("Contact form validation failed due to : " + errors.toString());
			return "contact";
		}
		contactService.saveMessageDetails(contact);
		/*
		contactService.setCounter(contactService.getCounter() + 1);
		log.info("number of hit to the contact form: "+contactService.getCounter());*/
		redirAttrs.addFlashAttribute("successMessage", "Thank you for contacting us. Will respond to your query soon!");
		return "redirect:/contact";
	}
	
	/*
	@RequestMapping("/displayMessages")
	public ModelAndView displayMessages(Model model) {
		List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
		ModelAndView modelAndView = new ModelAndView("messages.html");
		modelAndView.addObject("contactMsgs", contactMsgs);
		return modelAndView;
		
	}*/
	@RequestMapping("/displayMessages/page/{pageNum}")
    public ModelAndView displayMessages(Model model,
            @PathVariable(name = "pageNum") int pageNum,@RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir) {
        Page<Contact> msgPage = contactService.findMsgsWithOpenStatus(pageNum,sortField,sortDir);
        List<Contact> contactMsgs = msgPage.getContent();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", msgPage.getTotalPages());
        model.addAttribute("totalMsgs", msgPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactMsgs",contactMsgs);
        return modelAndView;
    }
	
	@RequestMapping(value="/closeMsg", method= {RequestMethod.GET})
	public String closeMsg(@RequestParam int id, Authentication authentication) {
		contactService.updateMsgStatus(id);
		return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
	}
	
	
	
	
	
		

}
