package model;

import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private int customerId;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Booking(int bookingId, int customerId, int roomId, LocalDate checkIn, LocalDate checkOut) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
    }

    public int getBookingDuration() {
        return checkOutDate.compareTo(checkInDate);
    }

    public double calculateCost(double dailyRate) {
        return getBookingDuration() * dailyRate;
    }

    public int getBookingId() {
        return bookingId;
    }
}
