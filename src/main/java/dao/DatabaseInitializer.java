package dao;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {

            // Create Customer table
            String customerTable = "CREATE TABLE IF NOT EXISTS Customer (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT NOT NULL" +
                    ");";
            stmt.execute(customerTable);

            // Create Room table
            String roomTable = "CREATE TABLE IF NOT EXISTS Room (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "roomNumber TEXT NOT NULL," +
                    "roomType TEXT NOT NULL," +
                    "price REAL NOT NULL" +
                    ");";
            stmt.execute(roomTable);

            // Create Booking table
            String bookingTable = "CREATE TABLE IF NOT EXISTS Booking (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "customerId INTEGER," +
                    "roomId INTEGER," +
                    "checkInDate TEXT," +
                    "checkOutDate TEXT," +
                    "FOREIGN KEY(customerId) REFERENCES Customer(id)," +
                    "FOREIGN KEY(roomId) REFERENCES Room(id)" +
                    ");";
            stmt.execute(bookingTable);

            System.out.println("✅ Database tables created successfully.");

        } catch (Exception e) {
            System.out.println("❌ Failed to initialize database: " + e.getMessage());
        }
    }
}
