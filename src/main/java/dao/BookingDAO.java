// BookingDAO.java
package dao;

import model.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public void addBooking(Booking b) {
        String sql = "INSERT INTO bookings (customerId, roomId, checkInDate, checkOutDate) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, b.getCustomerId());
            stmt.setInt(2, b.getRoomId());
            stmt.setString(3, b.getCheckInDate().toString());
            stmt.setString(4, b.getCheckOutDate().toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding booking: " + e.getMessage());
        }
    }

    public List<Booking> getBookingsByCustomer(int customerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE customerId = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking b = new Booking(
                    rs.getInt("id"),
                    rs.getInt("customerId"),
                    rs.getInt("roomId"),
                    LocalDate.parse(rs.getString("checkInDate")),
                    LocalDate.parse(rs.getString("checkOutDate"))
                );
                bookings.add(b);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving bookings: " + e.getMessage());
        }

        return bookings;
    }
}
