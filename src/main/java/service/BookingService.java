// BookingService.java
package service;

import model.Booking;
import model.Room;
import exception.RoomUnavailableException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class BookingService {
    private final Map<Integer, Booking> activeBookings = new HashMap<>();
    private int bookingIdCounter = 1;

    public Booking createBooking(int customerId, Room room, LocalDate checkIn, LocalDate checkOut)
            throws RoomUnavailableException {
        if (!roomIsAvailable(room)) {
            throw new RoomUnavailableException("Room " + room.getRoomId() + " is not available.");
        }

        Booking booking = new Booking(bookingIdCounter++, customerId, room.getRoomId(), checkIn, checkOut);
        room.book();
        activeBookings.put(booking.getBookingId(), booking);
        return booking;
    }

    public void cancelBooking(int bookingId, Room room) {
        if (activeBookings.containsKey(bookingId)) {
            activeBookings.remove(bookingId);
            room.release();
        }
    }

    public double getTotalCost(int bookingId, double dailyRate) {
        Booking booking = activeBookings.get(bookingId);
        if (booking == null) return 0;
        return booking.calculateCost(dailyRate);
    }

    public Map<Integer, Booking> getAllBookings() {
        return activeBookings;
    }

    private boolean roomIsAvailable(Room room) {
        // Simple logic based on object state
        return room.toString().contains("Available");
    }
}
