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
}