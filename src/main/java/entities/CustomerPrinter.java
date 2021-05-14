package entities;

import rdg.Customer;

public class CustomerPrinter {
    private static final CustomerPrinter INSTANCE = new CustomerPrinter();

    public static CustomerPrinter getInstance() { return INSTANCE; }

    private CustomerPrinter() { }

    public void print(Customer customer) {
        if (customer == null) {
            throw new NullPointerException("Customer is null");
        }

        System.out.print("ID: ");
        System.out.println(customer.getCustomerId());
        System.out.print("Birth number: ");
        System.out.println(customer.getBirthNumber());
        System.out.print("First name: ");
        System.out.println(customer.getFirstName());
        System.out.print("Last name: ");
        System.out.println(customer.getLastName());
        System.out.print("Address: ");
        System.out.println(customer.getAddress());
    }
}
