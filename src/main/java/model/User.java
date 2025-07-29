package model;

public abstract class User {
    public int userId;
    public String name;
    public String email;

    public abstract void showMenu();

    public void updateProfile() {
        System.out.println("Updating profile...");
    }

    // Getters and Setters
}
