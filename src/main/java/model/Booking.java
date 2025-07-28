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
        return (int) java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public double calculateCost(double dailyRate) {
        return getBookingDuration() * dailyRate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", roomId=" + roomId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
