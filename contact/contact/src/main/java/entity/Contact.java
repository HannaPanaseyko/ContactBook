package entity;

import java.util.Comparator;
import java.util.Objects;

public class Contact implements Comparable<Contact> {
    private int id;
    private String name;
    private String surName;
    private String phoneNumber;

    public Contact(){}

    public Contact(String name, String surName, String phoneNumber) {
        this.name = name;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurName() { return surName; }
    public void setSurName(String surName) { this.surName = surName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                Objects.equals(name, contact.name) &&
                Objects.equals(surName, contact.surName) &&
                Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surName, phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public int compareTo(Contact o) {
        return 0;
    }
    // DAO data acsessed object
}
