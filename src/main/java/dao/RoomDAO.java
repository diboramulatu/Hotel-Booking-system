// RoomDAO.java
package dao;

import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public List<Room> getAllAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE isAvailable = 1";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Room room = new Room(
                    rs.getInt("id"),
                    rs.getString("roomType"),
                    rs.getDouble("price")
                );
                rooms.add(room);
            }

        } catch (SQLException e) {
            System.out.println("Error loading rooms: " + e.getMessage());
        }

        return rooms;
    }

    public void updateRoomAvailability(int roomId, boolean isAvailable) {
        String sql = "UPDATE rooms SET isAvailable = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, isAvailable);
            stmt.setInt(2, roomId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating room: " + e.getMessage());
        }
    }
}
