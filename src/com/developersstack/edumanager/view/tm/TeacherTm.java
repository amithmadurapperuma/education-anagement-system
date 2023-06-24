package com.developersstack.edumanager.view.tm;

import javafx.scene.control.Button;

public class TeacherTm {
    private String teacherId;
    private String firstName;
    private String lastName;
    private String contact;
    private String address;
    private Button btnDelete;

    public TeacherTm() {
    }

    public TeacherTm(String teacherId, String firstName, String lastName, String contact, String address, Button btnDelete) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.address = address;
        this.btnDelete = btnDelete;
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

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "TeacherTm{" +
                "teacherId='" + teacherId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
