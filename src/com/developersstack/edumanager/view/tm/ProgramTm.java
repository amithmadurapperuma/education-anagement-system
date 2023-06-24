package com.developersstack.edumanager.view.tm;

import javafx.scene.control.Button;

public class ProgramTm {
    private String programCode;
    private String programName;
    private String teacherId;
    private Double cost;
    private Button btnTechView;
    private Button btnDelete;

    public ProgramTm() {
    }

    public ProgramTm(String programCode, String programName, String teacherId, Double cost, Button btnTechView, Button btnDelete) {
        this.programCode = programCode;
        this.programName = programName;
        this.teacherId = teacherId;
        this.cost = cost;
        this.btnTechView = btnTechView;
        this.btnDelete = btnDelete;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Button getBtnTechView() {
        return btnTechView;
    }

    public void setBtnTechView(Button btnTechView) {
        this.btnTechView = btnTechView;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "ProgramTm{" +
                "programCode='" + programCode + '\'' +
                ", programName='" + programName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", cost=" + cost +
                ", btnTechView=" + btnTechView +
                ", btnDelete=" + btnDelete +
                '}';
    }
}

