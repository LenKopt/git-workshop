package pl.akademiaspecjalistow.oop.library;

public class Reader {
    private String firstName;
    private String lastName;

    public Reader(String firstName, String lastName) {
        this.firstName = firstName.toUpperCase().trim();
        this.lastName = lastName.toUpperCase().trim();
    }

    @Override
    public String toString() {
        return "User: " + firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
