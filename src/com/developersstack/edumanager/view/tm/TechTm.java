package com.developersstack.edumanager.view.tm;

import javafx.scene.control.Button;

public class TechTm {
    private int tecId;
    private String techName;
    private Button btnDelete;

    public TechTm() {
    }

    public TechTm(int tecId, String techName, Button btnDelete) {
        this.tecId = tecId;
        this.techName = techName;
        this.btnDelete = btnDelete;
    }

    public int getTecId() {
        return tecId;
    }

    public void setTecId(int tecId) {
        this.tecId = tecId;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "TechTm{" +
                "tecId=" + tecId +
                ", techName='" + techName + '\'' +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
