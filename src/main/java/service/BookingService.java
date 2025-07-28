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

    // methods coming next...
}