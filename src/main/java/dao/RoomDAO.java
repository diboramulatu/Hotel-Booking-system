package dao;

import model.Room;
import java.util.List;

public class RoomDAO {
    public List<Room> getAllAvailableRooms() {
        return List.of();
    }

    public void updateRoomAvailability(int roomId, boolean status) {}

    public Room getRoomById(int roomId) {
        return null;
    }
}