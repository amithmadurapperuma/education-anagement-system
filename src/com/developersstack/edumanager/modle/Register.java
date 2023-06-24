package com.developersstack.edumanager.modle;

import java.util.Date;

public class Register {
    private String regId;
    private String student;
    private String program;
    private Date regDate;
    private Boolean payment;

    public Register() {
    }

    public Register(String regId, String student, String program, Date regDate, Boolean payment) {
        this.regId = regId;
        this.student = student;
        this.program = program;
        this.regDate = regDate;
        this.payment = payment;
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

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Register{" +
                "regId='" + regId + '\'' +
                ", student='" + student + '\'' +
                ", program='" + program + '\'' +
                ", regDate=" + regDate +
                ", payment=" + payment +
                '}';
    }
}
