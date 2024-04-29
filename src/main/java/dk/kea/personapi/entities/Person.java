package dk.kea.personapi.entities;

public class Person {

    String firstName;
    String middleName;
    String lastName;
    String fullName;

    public Person() {
    }

    public Person(String firstName, String middleName, String lastName) {
        this.firstName = capitalize(firstName);
        this.middleName = capitalize(middleName);
        this.lastName = capitalize(lastName);
    }

    public Person(String firstName, String middleName, String lastName, String fullName) {
        this.firstName = capitalize(firstName);
        this.middleName = capitalize(middleName);
        this.lastName = capitalize(lastName);
        this.fullName = capitalize(fullName);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = capitalize(firstName);
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = capitalize(middleName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = capitalize(lastName);
    }

    public String getFullName() {
        return getFirstName() + " " + (getMiddleName() != null ? getMiddleName() + " " : "") + getLastName();
    }

    public void setFullName(String fullName) {
        // Null
        if (fullName == null) return;

        int firstSpace = fullName.indexOf(" ");
        int lastSpace = fullName.lastIndexOf(" ");

        // First name only or Empty string
        if (firstSpace == -1) {
            setFirstName(fullName);
            setMiddleName(null);
            setLastName(null);
            return;
        }

        setFirstName(fullName.substring(0, firstSpace));
        setMiddleName((firstSpace != lastSpace ? fullName.substring(firstSpace + 1, lastSpace) : null));
        setLastName(fullName.substring(lastSpace + 1));
    }

    private String capitalize(String name) {
        if (name == null) return null;
        // Empty or one letter
        if (name.length() < 2) return name.toUpperCase();

        // Recursion, Hvis mellemnavn består af flere navne
        if (name.contains(" ")){
            int space = name.indexOf(" ");
            return capitalize(name.substring(0, space)) + " " + capitalize(name.substring(space + 1));
        }

        return name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
    }
}
