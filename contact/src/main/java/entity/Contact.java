package entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Contact implements Comparable<Contact> {
    private int id;
    private String name;
    private String surName;
    private String phoneNumber;
    private int age;
    private double height;
    private boolean maritalStatus;
    private Timestamp createDate;

    public Contact(){}

    public Contact(String name, String surName, String phoneNumber, int age,
                   double height, boolean maritalStatus, Timestamp createDate) {
        this.name = name;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.height = height;
        this.maritalStatus = maritalStatus;
        this.createDate = createDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurName() { return surName; }
    public void setSurName(String surName) { this.surName = surName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public double getHeight() {return height;}
    public void setHeight(double height) {this.height = height;}
    public boolean getMaritalStatus() {return maritalStatus;}
    public void setMaritalStatus(boolean maritalStatus) {this.maritalStatus = maritalStatus;}
    public Timestamp getCreateDate() {return createDate;}
    public void setCreateDate(Timestamp createDate) {this.createDate = createDate;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                age == contact.age &&
                Double.compare(contact.height, height) == 0 &&
                Boolean.compare(contact.maritalStatus, maritalStatus) == 0 &&
                Objects.equals(name, contact.name) &&
                Objects.equals(surName, contact.surName) &&
                Objects.equals(phoneNumber, contact.phoneNumber) &&
                Objects.equals(createDate, contact.createDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, surName, phoneNumber, age, height, maritalStatus, createDate);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", maritalStatus=" + maritalStatus +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public int compareTo(Contact o) {
        return 0;
    }

}
