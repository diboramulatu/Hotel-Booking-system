package model;

public class Admin extends User {
    @Override
    public void showMenu() {
        System.out.println("Admin Menu: 1. View Bookings 2. Manage Rooms 3. Logout");
    }
}
