package com.developersstack.edumanager.modle;

public class Teacher {
    private String teacherId;
    private String firstName;
    private String lastName;
    private String contact;
    private String address;

    public Teacher() {
    }

    public Teacher(String teacherId, String firstName, String lastName, String contact, String address) {
        this.setTeacherId(teacherId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setContact(contact);
        this.setAddress(address);
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
