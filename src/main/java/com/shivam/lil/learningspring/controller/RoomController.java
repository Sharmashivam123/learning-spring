package com.shivam.lil.learningspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.lil.learningspring.data.entity.Room;
import com.shivam.lil.learningspring.data.repo.RoomRepository;

@RestController
public class RoomController {

	@Autowired
	private RoomRepository roomRepository;
	
	@RequestMapping(value = "/rooms", method = {RequestMethod.GET})
	public Iterable<Room> getRooms(){
		return roomRepository.findAll();
	}
}
