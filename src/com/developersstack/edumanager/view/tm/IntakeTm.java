package com.developersstack.edumanager.view.tm;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class IntakeTm {
    private String intakeId;
    private String intakeName;
    private String startDate;
    private Button btnDelete;

    public IntakeTm() {
    }

    public IntakeTm(String intakeId, String intakeName, String startDate, Button btnDelete) {
        this.intakeId = intakeId;
        this.intakeName = intakeName;
        this.startDate = startDate;
        this.btnDelete = btnDelete;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "IntakeTm{" +
                "intakeId='" + intakeId + '\'' +
                ", intakeName='" + intakeName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
