package model;

public class SingleRoom extends Room {
    public SingleRoom(int roomId, double price) {
        super(roomId, "Single", price);
    }

    @Override
    public void book() {
        super.book();
        System.out.println("Single room booked.");
    }
}
