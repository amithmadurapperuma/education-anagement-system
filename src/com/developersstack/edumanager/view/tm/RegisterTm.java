package com.developersstack.edumanager.view.tm;

import javafx.scene.control.Button;

public class RegisterTm {
    private String regId;
    private String student;
    private String program;
    private String regDate;
    private Boolean payment;
    private Button btnDelete;

    public RegisterTm() {
    }

    public RegisterTm(String regId, String student, String program, String regDate, Boolean payment, Button btnDelete) {
        this.regId = regId;
        this.student = student;
        this.program = program;
        this.regDate = regDate;
        this.payment = payment;
        this.btnDelete = btnDelete;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "RegisterTm{" +
                "regId='" + regId + '\'' +
                ", student='" + student + '\'' +
                ", program='" + program + '\'' +
                ", regDate='" + regDate + '\'' +
                ", payment=" + payment +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
