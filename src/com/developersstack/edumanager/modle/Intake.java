package com.developersstack.edumanager.modle;

import java.util.Date;

public class Intake {
    private String intakeId;
    private String intakeName;
    private Date startDate;
    private Boolean intakeCompleteness;

    public Intake() {
    }

    public Intake(String intakeId, String intakeName, Date startDate, Boolean intakeCompleteness) {
        this.intakeId = intakeId;
        this.intakeName = intakeName;
        this.startDate = startDate;
        this.intakeCompleteness = intakeCompleteness;
    }

    public String getIntakeId() {
        return intakeId;
    }

    public void setIntakeId(String intakeId) {
        this.intakeId = intakeId;
    }

    public String getIntakeName() {
        return intakeName;
    }

    public void setIntakeName(String intakeName) {
        this.intakeName = intakeName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Boolean getIntakeCompleteness() {
        return intakeCompleteness;
    }

    public void setIntakeCompleteness(Boolean intakeCompleteness) {
        this.intakeCompleteness = intakeCompleteness;
    }

    @Override
    public String toString() {
        return "Intake{" +
                "intakeId='" + intakeId + '\'' +
                ", intakeName='" + intakeName + '\'' +
                ", startDate=" + startDate +
                ", intakeCompleteness=" + intakeCompleteness +
                '}';
    }
}
