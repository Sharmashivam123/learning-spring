package com.shivam.lil.learningspring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shivam.lil.learningspring.data.entity.Guest;
import com.shivam.lil.learningspring.data.repo.GuestRepo;

@Controller
public class GuestController {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(GuestController.class);
	
	@Autowired
	private GuestRepo guestRepo;
	
	@RequestMapping(value = "/guests", method = {RequestMethod.GET})
	public ModelAndView getGuests(){
		List<Guest> list = (List<Guest>) guestRepo.findAll();
		List<Guest> guests = list.stream().sorted((guest1, guest2)->guest1.getLastName().compareTo(guest2.getLastName())).collect(Collectors.toList());
		guests.stream().forEach((a)->log.info(a.toString()));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("guests", guests);
		modelAndView.setViewName("guests");
		return modelAndView;
	}
}
