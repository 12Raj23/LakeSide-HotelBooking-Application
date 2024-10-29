package com.daily.codework.lakeSide_hotel.service;

import java.util.List;

import com.daily.codework.lakeSide_hotel.model.BookedRoom;

public interface iBookingService {

	 void cancelBooking(Long bookingId);

	    List<BookedRoom> getAllBookingsByRoomId(Long roomId);

	    String saveBooking(Long roomId, BookedRoom bookingRequest);

	    BookedRoom findByBookingConfirmationCode(String confirmationCode);

	    List<BookedRoom> getAllBookings();

	    List<BookedRoom> getBookingsByUserEmail(String email);

		//List<BookedRoom> findBookingsByUserId(Long userId);

}
