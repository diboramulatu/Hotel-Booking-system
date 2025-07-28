package service;

import dao.RoomDAO;
import exception.ServiceException;
import model.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomService {
    private final RoomDAO roomDAO;
    private final Map<Integer, Room> cache = new HashMap<>();

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public List<Room> getAvailableRooms() throws ServiceException {
    try {
        List<Room> rooms = roomDAO.getAllAvailableRooms();
        cache.clear();
        for (Room r : rooms) {
            cache.put(r.getRoomId(), r); // assumes Room has getRoomId()
        }
        return rooms;
    } catch (Exception e) {
        throw new ServiceException("Failed to fetch available rooms", e);
    }
}
}