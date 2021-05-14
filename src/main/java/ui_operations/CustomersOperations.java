package ui_operations;

import printers.*;
import main.Utils;
import rdg.CustomerFinder;
import rdg.Customer;

import java.sql.SQLException;


public class CustomersOperations extends Operations {

    @Override
    public void invoke(int choice) throws SQLException{
        switch(choice) {
            case 0:
                printAllCustomers();
                break;
            case 1:
                printCustomerByBirthNumber();
                break;
            case 2:
                updateCustomerByBirthNumber();
                break;
        }
    }

    public void printAllCustomers() throws SQLException {
        for(Customer c: CustomerFinder.getInstance().findAll()) {
            CustomerPrinter.getInstance().print(c);
        }
    }

    public void printCustomerByBirthNumber() throws SQLException {
        String birthNumber = Utils.getStringFromInput("Enter birth number:");

        Customer c = CustomerFinder.getInstance().findByBirthNumber(birthNumber);
        if(c == null) {
            System.out.println("There is not customer with entered birth number");
        }
        else {
            CustomerPrinter.getInstance().print(c);
        }
    }

    public void updateCustomerByBirthNumber() throws SQLException {
        String birthNumber = Utils.getStringFromInput("Enter birth number:");

        Customer c = CustomerFinder.getInstance().findByBirthNumber(birthNumber);
        if(c == null) {
            System.out.println("There is not customer with entered birth number");
        }
        else {
            System.out.println("Update customer information(Leave blank to keep current):");
            String newFirstName = Utils.getStringFromInput("Enter new first name:");
            if(!newFirstName.isEmpty()) {
                c.setFirstName(newFirstName);
            }
            String newLastName = Utils.getStringFromInput("Enter new last name:");
            if(!newLastName.isEmpty()) {
                c.setLastName(newLastName);
            }
            String newAddress = Utils.getStringFromInput("Enter new address:");
            if(!newAddress.isEmpty()) {
                c.setAddress(newAddress);
            }

            c.update();
        }

    }

}
