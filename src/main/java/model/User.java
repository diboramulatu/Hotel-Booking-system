package model;

public abstract class User {
    protected int userId;
    protected String name;
    protected String email;

    public abstract void showMenu();

    public void updateProfile() {
        System.out.println("Updating profile...");
    }

    // Getters and Setters
}
