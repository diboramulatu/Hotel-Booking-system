package service;

import dao.BookingDAO;
import exception.*;
import model.Booking;

import java.time.LocalDate;
import java.util.List;

public class BookingService {
    private final BookingDAO bookingDAO;
    private final RoomService roomService;

    public BookingService(BookingDAO bookingDAO, RoomService roomService) {
        this.bookingDAO = bookingDAO;
        this.roomService = roomService;
    }

    public Booking createBooking(int customerId, int roomId, LocalDate checkIn, LocalDate checkOut)
        throws InvalidBookingException, RoomUnavailableException, ServiceException {

        try {
            InputValidator.requirePositiveId(customerId, "Customer ID");
            InputValidator.requirePositiveId(roomId, "Room ID");
            BookingRules.ensureValidDates(checkIn, checkOut);
        } catch (IllegalArgumentException e) {
            throw new InvalidBookingException(e.getMessage());
        }

    // Check room availability
        if (!roomService.isRoomAvailable(roomId)) {
            throw new RoomUnavailableException("Room " + roomId + " is not available.");
        }

        try {
            Booking booking = new Booking(0, customerId, roomId, checkIn, checkOut);
            bookingDAO.addBooking(booking);
            roomService.markBooked(roomId);
            return booking;
        } catch (Exception e) {
            roomService.markReleased(roomId); // rollback
            throw new ServiceException("Failed to create booking.", e);
        }
    }
}