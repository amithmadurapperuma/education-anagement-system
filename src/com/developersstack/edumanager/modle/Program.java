package com.developersstack.edumanager.modle;

import com.developersstack.edumanager.view.tm.TechTm;

import java.util.ArrayList;

public class Program {
    private String programCode;
    private String programName;
    private ArrayList<TechTm> technologies;
    private String teacherId;
    private Double cost;

    public Program() {
    }

    public Program(String programCode, String programName, ArrayList<TechTm> technologies, String teacherId, Double cost) {
        this.programCode = programCode;
        this.programName = programName;
        this.technologies = technologies;
        this.teacherId = teacherId;
        this.cost = cost;
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

    public ArrayList<TechTm> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(ArrayList<TechTm> technologies) {
        this.technologies = technologies;
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

    @Override
    public String toString() {
        return "Program{" +
                "programCode='" + programCode + '\'' +
                ", programName='" + programName + '\'' +
                ", technologies=" + technologies +
                ", teacherId='" + teacherId + '\'' +
                ", cost=" + cost +
                '}';
    }
}
