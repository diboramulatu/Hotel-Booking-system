package ui;

import model.*;
import service.*;
import exception.*;

import java.time.LocalDate;
import java.util.*;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingService bookingService = new BookingService();

        Room room1 = new SingleRoom(1, 100.0);
        Room room2 = new DoubleRoom(2, 150.0);
        Customer customer = new Customer();

        System.out.println("=== Welcome to the Hotel Booking System ===");

        while (true) {
            customer.showMenu();
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Check-in date (YYYY-MM-DD): ");
                        LocalDate in = LocalDate.parse(scanner.next());
                        System.out.print("Check-out date (YYYY-MM-DD): ");
                        LocalDate out = LocalDate.parse(scanner.next());

                        Booking booking = bookingService.createBooking(1, room1, in, out);
                        System.out.println("Booking successful: ID = " + booking.getBookingId());
                        break;

                    case 2:
                        System.out.print("Booking ID to cancel: ");
                        int id = scanner.nextInt();
                        bookingService.cancelBooking(id, room1);
                        System.out.println("Booking cancelled.");
                        break;

                    case 3:
                        System.out.println("Goodbye!");
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
