import React, { useState, useEffect } from "react";
import moment from "moment";
import Button from "react-bootstrap/Button";
import { useNavigate } from "react-router-dom";

const BookingSummary = ({
  booking = {},
  payment = 0,
  isFormValid,
  onConfirm,
}) => {
  const checkInDate = booking.checkInDate ? moment(booking.checkInDate) : null;
  const checkOutDate = booking.checkOutDate
    ? moment(booking.checkOutDate)
    : null;
  const numberOfDays =
    checkInDate && checkOutDate ? checkOutDate.diff(checkInDate, "days") : 0;
  const [isBookingConfirmed, setIsBookingConfirmed] = useState(false);
  const [isProcessingPayment, setIsProcessingPayment] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    console.log("Booking:", booking);
    console.log("Payment:", payment);
    console.log("isFormValid:", isFormValid);
  }, [booking, payment, isFormValid]);

  const handleConfirmBooking = () => {
    setIsProcessingPayment(true);
    setTimeout(() => {
      setIsProcessingPayment(false);
      setIsBookingConfirmed(true);
      onConfirm();
    }, 3000);
  };

  useEffect(() => {
    if (isBookingConfirmed) {
      navigate("/booking-success");
    }
  }, [isBookingConfirmed, navigate]);

  return (
    <div className="row">
      <div className="col-md-6"></div>
      <div className="card card-body mt-5">
        <h4 className="card-title hotel-color">Reservation Summary</h4>
        <p>
          Name: <strong>{booking.guestFullName || "N/A"}</strong>
        </p>
        <p>
          Email: <strong>{booking.guestEmail || "N/A"}</strong>
        </p>
        <p>
          Check-in Date:{" "}
          <strong>
            {checkInDate ? checkInDate.format("MMM Do YYYY") : "N/A"}
          </strong>
        </p>
        <p>
          Check-out Date:{" "}
          <strong>
            {checkOutDate ? checkOutDate.format("MMM Do YYYY") : "N/A"}
          </strong>
        </p>
        <p>
          Number of Days Booked: <strong>{numberOfDays}</strong>
        </p>

        <div>
          <h5 className="hotel-color">Number of Guests</h5>
          <strong>
            Adult{booking.numOfAdults > 1 ? "s" : ""}:{" "}
            {booking.numOfAdults || 0}
          </strong>
          <strong>
            <p>Children: {booking.numOfChildren || 0}</p>
          </strong>
        </div>

        {payment > 0 ? (
          <>
            <p>
              Total payment: <strong>${payment}</strong>
            </p>

            {isFormValid && !isBookingConfirmed ? (
              <Button variant="success" onClick={handleConfirmBooking}>
                {isProcessingPayment ? (
                  <>
                    <span
                      className="spinner-border spinner-border-sm mr-2"
                      role="status"
                      aria-hidden="true"
                    ></span>
                    Booking Confirmed, redirecting to payment...
                  </>
                ) : (
                  "Confirm Booking & proceed to payment"
                )}
              </Button>
            ) : isBookingConfirmed ? (
              <div className="d-flex justify-content-center align-items-center">
                <div className="spinner-border text-primary" role="status">
                  <span className="sr-only">Loading...</span>
                </div>
              </div>
            ) : null}
          </>
        ) : (
          <p className="text-danger">
            Check-out date must be after check-in date.
          </p>
        )}
      </div>
    </div>
  );
};

export default BookingSummary;
