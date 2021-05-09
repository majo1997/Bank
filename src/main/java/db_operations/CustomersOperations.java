package db_operations;

import entities.Customer;
import entities.Customers;
import entities.InvalidValueException;
import entities.Utils;


public class CustomersOperations extends Operations {
    @Override
    public void invoke1() {
        Customers customers = new Customers();
        customers.print();
    }

    @Override
    public void invoke2() {
        String birthNumber = Utils.getStringFromInput("Enter birth number:");

        try {
            Customer c = new Customer(birthNumber);
            System.out.println(c);
        }
        catch(InvalidValueException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void invoke3() {
        String birthNumber = Utils.getStringFromInput("Enter birth number:");//todo spytat sa co si mam tu pytat id/rodne c.

        try {
            Customer c = new Customer(birthNumber);

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

            System.out.println("Customer (" + c.toString() + ") updated successfully");
        }
        catch(InvalidValueException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void invoke4() {

    }

    @Override
    public void invoke5() {

    }

    @Override
    public void invoke6() {

    }

    @Override
    public void invoke7() {

    }

    @Override
    public void invoke8() {

    }

    @Override
    public void invoke9() {

    }
}
