package ui;

import dao.BookingDAO;
import dao.RoomDAO;
import model.Booking;
import model.Room;
import service.BookingService;
import service.RoomService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class RoomBookingGUI extends JFrame {
    private JComboBox<Room> roomComboBox;
    private JSpinner checkInSpinner;
    private JSpinner checkOutSpinner;
    private JLabel totalLabel;

    public RoomBookingGUI() {
        setTitle("Hotel Room Booking");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Load rooms from DB for a default 3-day window
        RoomDAO roomDAO = new RoomDAO();
        LocalDate today = LocalDate.now();
        LocalDate later = today.plusDays(3);
        List<Room> availableRooms = roomDAO.getAllAvailableRooms(today, later);

        // Room selection
        add(new JLabel("Select Room:"));
        roomComboBox = new JComboBox<>(availableRooms.toArray(new Room[0]));
        add(roomComboBox);

        // Check-in date
        add(new JLabel("Check-in Date:"));
        checkInSpinner = new JSpinner(new SpinnerDateModel());
        checkInSpinner.setEditor(new JSpinner.DateEditor(checkInSpinner, "yyyy-MM-dd"));
        checkInSpinner.setValue(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        add(checkInSpinner);

        // Check-out date
        add(new JLabel("Check-out Date:"));
        checkOutSpinner = new JSpinner(new SpinnerDateModel());
        checkOutSpinner.setEditor(new JSpinner.DateEditor(checkOutSpinner, "yyyy-MM-dd"));
        checkOutSpinner.setValue(Date.from(later.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        add(checkOutSpinner);

        // Total price
        add(new JLabel("Total Price:"));
        totalLabel = new JLabel("0.00");
        add(totalLabel);

        // Calculate Total button
        JButton calcButton = new JButton("Calculate Total");
        calcButton.addActionListener(e -> calculateTotal());
        add(calcButton);

        // Book Room button
        JButton bookButton = new JButton("Book Room");
        bookButton.addActionListener(e -> bookRoom());
        add(bookButton);

        setVisible(true);
    }

    private void calculateTotal() {
        Room selectedRoom = (Room) roomComboBox.getSelectedItem();
        if (selectedRoom == null) {
            totalLabel.setText("Select a room");
            return;
        }

        LocalDate checkIn = getSelectedDate(checkInSpinner);
        LocalDate checkOut = getSelectedDate(checkOutSpinner);

        long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (days <= 0) {
            totalLabel.setText("Invalid dates");
            return;
        }

        double totalPrice = selectedRoom.getPrice() * days;
        totalLabel.setText(String.format("%.2f", totalPrice));
    }

    private void bookRoom() {
        Room selectedRoom = (Room) roomComboBox.getSelectedItem();
        if (selectedRoom == null) {
            JOptionPane.showMessageDialog(this, "Select a room first.");
            return;
        }

        LocalDate checkIn = getSelectedDate(checkInSpinner);
        LocalDate checkOut = getSelectedDate(checkOutSpinner);
        int customerId = 1; // Replace with actual customer input logic

        try {
            BookingService bookingService = new BookingService(new BookingDAO(), new RoomService(new RoomDAO()));
            Booking booking = bookingService.createBooking(customerId, selectedRoom.getRoomId(), checkIn, checkOut);
            JOptionPane.showMessageDialog(this, "Room booked! Booking ID: " + booking.getBookingId());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Booking failed: " + e.getMessage());
        }
    }

    private LocalDate getSelectedDate(JSpinner spinner) {
        Date date = (Date) spinner.getValue();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RoomBookingGUI::new);
    }
}
