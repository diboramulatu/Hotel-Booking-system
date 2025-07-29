package ui;

import dao.CustomerDAO;
import dao.DatabaseInitializer;
import model.Customer;

import java.util.List;

public class DatabaseTest {
    public static void main(String[] args) {
        // Step 1: Create DB and tables
        DatabaseInitializer.initialize();

        // Step 2: Insert a test customer
        Customer testCustomer = new Customer();
        testCustomer.name = "Alice Wonderland";
        testCustomer.email = "alice@example.com";

        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.addCustomer(testCustomer);

        // Step 3: Retrieve and print all customers
        List<Customer> allCustomers = customerDAO.getAllCustomers();
        System.out.println("=== All Customers ===");
        for (Customer c : allCustomers) {
            System.out.println("ID: " + c.userId + ", Name: " + c.name + ", Email: " + c.email);
        }
    }
}
