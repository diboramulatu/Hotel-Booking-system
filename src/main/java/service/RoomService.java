// RoomService.java
package service;

import model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomService {
    private final List<Room> roomList = new ArrayList<>();

    public void addRoom(Room room) {
        roomList.add(room);
    }

    public List<Room> getAvailableRooms() {
        return roomList.stream()
                .filter(r -> r.toString().contains("Available"))
                .collect(Collectors.toList());
    }

    public Room getRoomById(int id) {
        return roomList.stream()
                .filter(r -> r.getRoomId() == id)
                .findFirst()
                .orElse(null);
    }

    public void markRoomAsBooked(int roomId) {
        Room room = getRoomById(roomId);
        if (room != null) {
            room.book();
        }
    }

    public void releaseRoom(int roomId) {
        Room room = getRoomById(roomId);
        if (room != null) {
            room.release();
        }
    }

    public List<Room> getAllRooms() {
        return roomList;
    }
}
