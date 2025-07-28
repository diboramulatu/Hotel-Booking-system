package dao;

import model.Customer;
import java.util.List;
import java.util.ArrayList;

public class CustomerDAO {
    
    public void addCustomer(Customer customer) {
        // TODO: Implement database insertion
        System.out.println("Customer added: " + customer);
    }
    
    public Customer getCustomerById(int customerId) {
        // TODO: Implement database retrieval
        return null;
    }
    
    public List<Customer> getAllCustomers() {
        // TODO: Implement database retrieval
        return new ArrayList<>();
    }
    
    public void updateCustomer(Customer customer) {
        // TODO: Implement database update
        System.out.println("Customer updated: " + customer);
    }
    
    public void deleteCustomer(int customerId) {
        // TODO: Implement database deletion
        System.out.println("Customer deleted: " + customerId);
    }
}
