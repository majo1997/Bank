package entities;

public class Customer implements Entity {

    private Integer customerId;
    private String birthNumber;
    private String firstName;
    private String lastName;
    private String address;

    public Customer(Integer customerId, String birthNumber, String firstName, String lastName, String address) {
        this.customerId = customerId;
        this.birthNumber = birthNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public void update() {

    }

    @Override
    public void insert() {

    }

    @Override
    public void delete() {

    }

    @Override
    public String toString() {
        return customerId + ". " + birthNumber + ", " + firstName + " " + lastName + ", " + address;
    }

    public void showAccounts() {

    }
}
