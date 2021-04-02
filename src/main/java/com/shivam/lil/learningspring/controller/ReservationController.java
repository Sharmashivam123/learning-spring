package com.shivam.lil.learningspring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shivam.lil.learningspring.business.domain.RoomReservation;
import com.shivam.lil.learningspring.business.service.ReservationService;
import com.shivam.lil.learningspring.data.entity.Reservation;
import com.shivam.lil.learningspring.data.repo.ReservationRepo;

@Controller
public class ReservationController {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	private ReservationRepo reservationRepo;
	
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(value = "/reservations", method = {RequestMethod.GET})
	public Iterable<Reservation> getReservations(){
		return reservationRepo.findAll();
	}
	
	@RequestMapping(value = "/reservations/{date}", method = {RequestMethod.GET})
	public ModelAndView getRoomReservationBydate(@PathVariable("date") String date) throws ParseException{
		ModelAndView modelAndView = new ModelAndView("reservations");
		List<RoomReservation> roomReservations = reservationService.getRoomReservationsForDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		modelAndView.addObject("roomReservations",roomReservations);
		roomReservations.stream().forEach(a->log.info(a.toString()));
		return modelAndView;
	}
}
