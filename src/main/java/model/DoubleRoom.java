package model;

public class DoubleRoom extends Room {
    public DoubleRoom(int roomId, double price) {
        super(roomId, "Double", price);
    }

    @Override
    public void book() {
        super.book();
        System.out.println("Double room booked.");
    }
}
