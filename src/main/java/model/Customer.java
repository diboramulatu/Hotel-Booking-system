package model;

public class Customer extends User {
    @Override
    public void showMenu() {
        System.out.println("Customer Menu: 1. Book Room 2. Cancel Booking 3. Logout");
    }
}
