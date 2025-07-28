package model;

public class Room {
    private int roomId;
    private String roomType;
    private double price;
    private boolean isAvailable = true;

    public Room(int roomId, String roomType, double price) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.price = price;
    }

    public void book() {
        isAvailable = false;
    }

    public void release() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room " + roomId + " (" + roomType + ") - $" + price + " - " + (isAvailable ? "Available" : "Booked");
    }

    public int getRoomId() {
        return roomId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

}
