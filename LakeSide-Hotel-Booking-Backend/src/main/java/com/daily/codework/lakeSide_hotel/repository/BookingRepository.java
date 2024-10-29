package com.daily.codework.lakeSide_hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daily.codework.lakeSide_hotel.model.BookedRoom;
  

/**
 * Author: Rajesh R
 */
public interface BookingRepository extends JpaRepository<BookedRoom, Long>{

	 List<BookedRoom> findByRoomId(Long roomId);

	 Optional<BookedRoom> findByBookingConfirmationCode(String confirmationCode);

	    List<BookedRoom> findByGuestEmail(String email);


	   
}