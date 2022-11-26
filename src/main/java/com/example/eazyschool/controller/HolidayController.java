package com.example.eazyschool.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.eazyschool.model.Holiday;
import com.example.eazyschool.repository.HolidayRepository;

@Controller
public class HolidayController {
	
	 @Autowired
	    private HolidayRepository holidayRepository;

	
	/*
	//without passing any RequestParam or Path variable, by default it will display all the holidays details of both catagory
	@GetMapping("/holidays")
	public String displayHoliday(Model model) {
		
		List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ","New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31 ","Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24 ","Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25 ","Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Jan 17 ","Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday(" July 4 ","Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sep 5 ","Labor Day", Holiday.Type.FEDERAL),
                new Holiday(" Nov 11 ","Veterans Day", Holiday.Type.FEDERAL)
        );
		
		//Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : Holiday.Type.values()) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
		
		return "holidays";
	}*/
	
	//with RequestParam
	@GetMapping("/holidays")
	public String displayHoliday(@RequestParam(required=false) boolean festival,@RequestParam(required=false) boolean federal, Model model) {
		
		model.addAttribute("festival", festival);
		model.addAttribute("federal", federal);
		Iterable<Holiday> holidays = holidayRepository.findAll();
		List<Holiday> holidayList = StreamSupport
                .stream(holidays.spliterator(), false)
                .collect(Collectors.toList());
		//Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : Holiday.Type.values()) {
            model.addAttribute(type.toString(),
                    (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
		
		return "holidays";
	}
	

}
