package com.daily.codework.lakeSide_hotel.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.codework.lakeSide_hotel.exception.InvalidBookingRequestException;
import com.daily.codework.lakeSide_hotel.exception.ResourceNotFoundException;
import com.daily.codework.lakeSide_hotel.model.BookedRoom;
import com.daily.codework.lakeSide_hotel.model.Room;
import com.daily.codework.lakeSide_hotel.response.BookingResponse;
import com.daily.codework.lakeSide_hotel.response.RoomResponse;
import com.daily.codework.lakeSide_hotel.service.BookingService;
import com.daily.codework.lakeSide_hotel.service.IRoomService;
import com.daily.codework.lakeSide_hotel.service.iBookingService;

import lombok.RequiredArgsConstructor;





/**
 * Author: Rajesh R
 */
@CrossOrigin(origins="*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
	private final iBookingService bookingService;
	private final IRoomService roomService;
	
	@Autowired
    public BookingController(iBookingService bookingService, IRoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }
	
	
	@GetMapping("all-bookings")
	public ResponseEntity<List<BookingResponse>>getAllBookings(){
		List<BookedRoom>bookings=bookingService.getAllBookings();
		List<BookingResponse>bookingResponses=new ArrayList<>();
		for(BookedRoom booking:bookings) {
			BookingResponse bookingResponse=getBookingResponse(booking);
			bookingResponses.add(bookingResponse);
			
		}
		return ResponseEntity.ok(bookingResponses);
		
		
	}
	
	@GetMapping("/confirmation/{confirmationCode}")
	public ResponseEntity<?>getBookingByConfirmationCode(@PathVariable String confirmationCode){
		try {
			BookedRoom booking=bookingService.findByBookingConfirmationCode(confirmationCode);
			BookingResponse bookingResponse=getBookingResponse(booking);
			return ResponseEntity.ok(bookingResponse);
			
		}catch(ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
			
		}
	}
	
	
	@GetMapping("/user/{email}/bookings")
    public ResponseEntity<List<BookingResponse>> getBookingsByUserEmail(@PathVariable String email) {
        List<BookedRoom> bookings = bookingService.getBookingsByUserEmail(email);
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (BookedRoom booking : bookings) {
            BookingResponse bookingResponse = getBookingResponse(booking);
            bookingResponses.add(bookingResponse);
        }
        return ResponseEntity.ok(bookingResponses);
    }
	
	@PostMapping("/room/{roomId}/booking")
	public ResponseEntity<?>saveBooking(@PathVariable Long roomId,
			                            @RequestBody BookedRoom bookingRequest){
		try {
			String confirmationCode= bookingService.saveBooking(roomId,bookingRequest);
			return ResponseEntity.ok("Room booked  succesfully,your booking confirmation code is:"+confirmationCode);
		}
	catch(InvalidBookingRequestException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
		
	}
	}
	@DeleteMapping("/booking/{bookingId}/delete")
	public void cancelBooking(Long bookingId) {
		bookingService.cancelBooking(bookingId);
	}
	


	


	
	 private BookingResponse getBookingResponse(BookedRoom booking) {
	        Room theRoom = roomService.getRoomById(booking.getRoom().getId()).get();
	        RoomResponse room = new RoomResponse(
	                theRoom.getId(),
	                theRoom.getRoomType(),
	                theRoom.getRoomPrice());
	        return new BookingResponse(
	                booking.getBookingId(), booking.getCheckInDate(),
	                booking.getCheckOutDate(),booking.getGuestFullName(),
	                booking.getGuestEmail(), booking.getNumOfAdults(),
	                booking.getNumOfChildren(), booking.getTotalNumOfGuest(),
	                booking.getBookingConfirmationCode(), room);
	    }
	}