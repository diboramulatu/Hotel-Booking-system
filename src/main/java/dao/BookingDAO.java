package dao;

import model.Booking;
import java.util.List;

public class BookingDAO {
    public void addBooking(Booking b) {}
    public void cancelBooking(int bookingId) {}
    public List<Booking> getBookingsByCustomer(int customerId) {
        return List.of(); // empty list for now
    }
}