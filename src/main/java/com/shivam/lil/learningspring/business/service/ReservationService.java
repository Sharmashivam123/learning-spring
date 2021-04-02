package com.shivam.lil.learningspring.business.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.lil.learningspring.business.domain.RoomReservation;
import com.shivam.lil.learningspring.data.entity.Guest;
import com.shivam.lil.learningspring.data.entity.Reservation;
import com.shivam.lil.learningspring.data.entity.Room;
import com.shivam.lil.learningspring.data.repo.GuestRepo;
import com.shivam.lil.learningspring.data.repo.ReservationRepo;
import com.shivam.lil.learningspring.data.repo.RoomRepository;

@Service
public class ReservationService {

	private final RoomRepository roomRepository;
	private final GuestRepo guestRepo;
	private final ReservationRepo reservationRepo;
	
	@Autowired
	public ReservationService(RoomRepository roomRepository, GuestRepo guestRepo, ReservationRepo reservationRepo) {
		super();
		this.roomRepository = roomRepository;
		this.guestRepo = guestRepo;
		this.reservationRepo = reservationRepo;
	}

	public List<RoomReservation> getRoomReservationsForDate(Date date){
		Iterable<Room> rooms = roomRepository.findAll();
		Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
		rooms.forEach(room->{
			Function<Room, RoomReservation> reservedRoom = getReservedRoom();
			roomReservationMap.put(room.getRoomId(), reservedRoom.apply(room));
		});
		
		Iterable<Reservation> reservations = reservationRepo.findReservationByDate(new java.sql.Date(date.getTime()));
		
		reservations.forEach(reservation->{
			RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
			Guest guest = guestRepo.findById(reservation.getGuestId()).get();
			roomReservation.setDate(reservation.getResDate());
			roomReservation.setFirstName(guest.getFirstName());
			roomReservation.setLastName(guest.getLastName());
			roomReservation.setGuestId(guest.getGuestId());
		});
		
		List<RoomReservation> list = roomReservationMap.values().stream().filter(a->a.getDate()!=null).collect(Collectors.toList());
		
		return list;
	}
	
	private Function<Room, RoomReservation> getReservedRoom(){
		return room -> {RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoomId(room.getRoomId());
			roomReservation.setRoomName(room.getName());
			roomReservation.setRoomNumber(room.getRoomNumber());
			return roomReservation;
		};
	}
	
}
