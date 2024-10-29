import { parseISO } from "date-fns";
import React, { useState, useEffect } from "react";
import DateSlider from "../common/DateSlider";

const BookingsTable = ({ bookingInfo = [], handleBookingCancellation }) => {
  const [filteredBookings, setFilteredBookings] = useState(bookingInfo);

  const filterBookings = (startDate, endDate) => {
    if (!startDate || !endDate) {
      setFilteredBookings(bookingInfo);
      return;
    }

    const filtered = bookingInfo.filter((booking) => {
      const bookingStartDate = parseISO(booking.checkInDate);
      const bookingEndDate = parseISO(booking.checkOutDate);
      return (
        bookingStartDate >= startDate &&
        bookingEndDate <= endDate &&
        bookingEndDate > startDate
      );
    });

    console.log("Filtered Bookings:", filtered); // Log filtered bookings
    setFilteredBookings(filtered);
  };

  useEffect(() => {
    setFilteredBookings(bookingInfo);
  }, [bookingInfo]);

  return (
    <section className="p-4">
      <DateSlider
        onDateChange={filterBookings}
        onFilterChange={filterBookings}
      />
      <table className="table table-bordered table-hover shadow">
        <thead>
          <tr>
            <th>S/N</th>
            <th>Booking ID</th>
            <th>Room ID</th>
            <th>Room Type</th>
            <th>Check-In Date</th>
            <th>Check-Out Date</th>
            <th>Guest Name</th>
            <th>Guest Email</th>
            <th>Adults</th>
            <th>Children</th>
            <th>Total Guest</th>
            <th>Confirmation Code</th>
            <th colSpan={2}>Actions</th>
          </tr>
        </thead>
        <tbody className="text-center">
          {filteredBookings.map((booking, index) => (
            <tr key={booking.id || `booking-${index}`}>
              <td>{index + 1}</td>
              <td>{booking.id || "N/A"}</td>
              <td>{booking.room?.id || "N/A"}</td>
              <td>{booking.room?.roomType || "N/A"}</td>
              <td>{booking.checkInDate || "N/A"}</td>
              <td>{booking.checkOutDate || "N/A"}</td>
              <td>{booking.guestName || "N/A"}</td>
              <td>{booking.guestEmail || "N/A"}</td>
              <td>{booking.numOfAdults || 0}</td>
              <td>{booking.numOfChildren || 0}</td>
              <td>{booking.totalNumOfGuests || 0}</td>
              <td>{booking.bookingConfirmationCode || "N/A"}</td>
              <td>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => handleBookingCancellation(booking.id)}
                >
                  Cancel
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {filteredBookings.length === 0 && (
        <p>No booking found for the selected dates</p>
      )}
    </section>
  );
};

export default BookingsTable;
