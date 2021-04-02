package com.shivam.lil.learningspring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.shivam.lil.learningspring.business.domain.RoomReservation;
import com.shivam.lil.learningspring.business.service.ReservationService;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

	@MockBean
	private ReservationService reservationService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void reservationControllerTest() throws Exception {
		RoomReservation expectedRoomReservation =  new RoomReservation();
		java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01");
		expectedRoomReservation.setDate(date);
		expectedRoomReservation.setFirstName("Young");
		expectedRoomReservation.setLastName("Judith");
		expectedRoomReservation.setRoomNumber("C2");
		expectedRoomReservation.setRoomName("Cambridge");
		List<RoomReservation> expectedList = new ArrayList<>();
		expectedList.add(expectedRoomReservation);
		
		Mockito.when(reservationService.getRoomReservationsForDate(date)).thenReturn(expectedList);
		mockMvc.perform(get("/reservations/2020-01-01")).andExpect(status().isOk());
	}
	
}
