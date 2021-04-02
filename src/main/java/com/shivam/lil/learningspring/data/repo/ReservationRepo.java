package com.shivam.lil.learningspring.data.repo;

import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shivam.lil.learningspring.data.entity.Reservation;

@Repository
public interface ReservationRepo extends CrudRepository<Reservation, Long> {
	
	@Query(nativeQuery = true, value = "select * from Reservation where RES_DATE=?")
	Iterable<Reservation> findReservationByDate(Date date);

}
