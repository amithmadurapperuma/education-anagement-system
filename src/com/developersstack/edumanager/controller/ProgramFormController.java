package com.developersstack.edumanager.controller;

import com.developersstack.edumanager.db.Database;
import com.developersstack.edumanager.modle.Program;
import com.developersstack.edumanager.modle.Teacher;
import com.developersstack.edumanager.view.tm.ProgramTm;
import com.developersstack.edumanager.view.tm.TeacherTm;
import com.developersstack.edumanager.view.tm.TechTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ProgramFormController {
    public AnchorPane context;
    public TextField txtProgramCode;
    public TextField txtProgramName;
    public ComboBox<String> cmbTechnologies;
    public ComboBox<String> cmbTeachers;
    public TextField txtCost;
    public TableView<ProgramTm> tblProgramData;
    public TableColumn colId;
    public TableColumn colProgramName;
    public TableColumn colTechnologies;
    public TableColumn colTeachers;
    public TableColumn colCost;
    public TextField txtSearch;
    public TableColumn colAction;
    public Button btnSave;

    public String searchText = "";
    public TextField txtTechnology;
    public TableView<TechTm> tblTechnology;
    public TableColumn colTechId;
    public TableColumn colTechName;
    public TableColumn colActionBtn;
    ObservableList<TechTm> techObList = FXCollections.observableArrayList();

    public void initialize(){
        colTechId.setCellValueFactory(new PropertyValueFactory<>("tecId"));
        colTechName.setCellValueFactory(new PropertyValueFactory<>("techName"));
        colActionBtn.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        colId.setCellValueFactory(new PropertyValueFactory<>("programCode"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colTechnologies.setCellValueFactory(new PropertyValueFactory<>("btnTechView"));
        colTeachers.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        setProgramCode();
        setTeachers();
        setTblProgramData(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setTblProgramData(searchText);
        });

        tblProgramData.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null != newValue){
                setTblDataToTextField(newValue);
                btnSave.setText("Update");
            }
        });
    }

    public void btnBacktoDashboardOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        if(btnSave.getText().equals("Save")){
            Program program = new Program(
                    txtProgramCode.getText(),
                    txtProgramName.getText(),
                    new ArrayList<>(techObList),
                    Database.teacherTable.stream().filter(e-> e.getFirstName().equals(cmbTeachers.getValue())).
                            findFirst().get().getTeacherId(),
                    Double.parseDouble(txtCost.getText())
            );
            Database.programTable.add(program);
            setTblProgramData(searchText);
            setProgramCode();
            clearTextField();
            techObList.clear();
            tblTechnology.refresh();
        }else{
            for (Program program: Database.programTable) {
                if(program.getProgramCode().equals(txtProgramCode.getText())){
                    program.setProgramName(txtProgramName.getText());
                    program.setTechnologies(new ArrayList<>(techObList));
                    program.setTeacherId(Database.teacherTable.stream().filter(e-> e.getFirstName().
                            equals(cmbTeachers.getValue())).findFirst().get().getTeacherId());
                    program.setCost(Double.parseDouble(txtCost.getText()));
                    setTblProgramData(searchText);
                    setProgramCode();
                    clearTextField();
                    btnSave.setText("Save");
                    return;
                }
                new Alert(Alert.AlertType.INFORMATION, "Record Not Found!").show();
            }
        }
    }

    public void newProgramOnAction(ActionEvent actionEvent) {
        clearTextField();
        btnSave.setText("Save");
        techObList.clear();
        tblTechnology.refresh();
    }

    public void btnClearTechOnAction(ActionEvent actionEvent) {
        techObList.clear();
        tblTechnology.refresh();
    }
    public void addTechOnAction(ActionEvent actionEvent) {
            if(!isExists(txtTechnology.getText().trim())){
                Button btnDelete = new Button("Delete");
                TechTm tech = new  TechTm(
                        techObList.size() + 1,
                        txtTechnology.getText(),
                        btnDelete
                );
                btnDelete.setOnAction(e->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ary Your Sure?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get().equals(ButtonType.YES)){
                        techObList.remove(tech);
                        tblTechnology.refresh();
                    }
                });
                techObList.add(tech);
                tblTechnology.setItems(techObList);
                txtTechnology.clear();
            }else {
                txtTechnology.selectAll();
                new Alert(Alert.AlertType.WARNING, "Already Exists!").show();
            }
    }

    private boolean isExists(String tech){
        for (TechTm techTm: techObList) {
            if (techTm.getTechName().equalsIgnoreCase(tech)){
                return true;
            }
        }
        return false;
    }

    public void setTeachers(){
        ArrayList<String> teachersArray = new ArrayList<>();
        for (Teacher teacher: Database.teacherTable) {
            teachersArray.add(teacher.getFirstName());
        }
        ObservableList<String> obList = FXCollections.observableArrayList(teachersArray);
        cmbTeachers.setItems(obList);
    }

    public void setProgramCode(){
        if(!Database.programTable.isEmpty()){
            Program lastProgramCodeIndex = Database.programTable.get(Database.programTable.size() - 1);
            String lastProgramCode = lastProgramCodeIndex.getProgramCode();
            String[] splitData = lastProgramCode.split("-");
            String programCodeIntegerAsString = splitData[1];
            int programCodeIntegerAsInteger = Integer.parseInt(programCodeIntegerAsString);
            programCodeIntegerAsInteger++;
            txtProgramCode.setText("P-" + programCodeIntegerAsInteger);
        }else {
            txtProgramCode.setText("P-1");
        }
    }

    public void setTblProgramData(String searchText){
        ObservableList<ProgramTm> obList = FXCollections.observableArrayList();
        for (Program program: Database.programTable) {
            if(program.getProgramName().contains(searchText)){
                Button btnTechView = new Button("Tech View");
                Button btnDelete = new Button("Delete");
                ProgramTm programTm = new ProgramTm(
                        program.getProgramCode(),
                        program.getProgramName(),
                        Database.teacherTable.stream().filter(e-> e.getTeacherId().equals(program.getTeacherId())).
                                findFirst().get().getFirstName(),
                        program.getCost(),
                        btnTechView,
                        btnDelete
                );
                btnDelete.setOnAction(e->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?", ButtonType.YES,
                            ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if(buttonType.get().equals(ButtonType.YES)){
                        Database.programTable.remove(program);
                        setTblProgramData(searchText);
                        tblTechnology.refresh();
                    }else {
                        new Alert(Alert.AlertType.INFORMATION, "Record Not Found!");
                    }
                });
                btnTechView.setOnAction(e->{
                    techObList.clear();
                    tblTechnology.refresh();
                    for (TechTm tech: program.getTechnologies()) {
                        Button btnRemove = new Button("Delete");
                        TechTm techTm = new TechTm(
                                tech.getTecId(),
                                tech.getTechName(),
                                btnRemove
                        );
                        btnRemove.setOnAction(ex->{
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?",
                                    ButtonType.YES, ButtonType.NO);
                            Optional<ButtonType> buttonType = alert.showAndWait();
                            if (buttonType.get().equals(ButtonType.YES)){
                                techObList.remove(techTm);
                                tblTechnology.refresh();
                            }
                        });
                        techObList.add(techTm);
                    }
                    tblTechnology.setItems(techObList);
                });
                obList.add(programTm);
            }
        }
        tblProgramData.setItems(obList);
    }

    public void setTblDataToTextField(ProgramTm programTm){
        txtProgramCode.setText(programTm.getProgramCode());
        txtProgramName.setText(programTm.getProgramName());
        cmbTeachers.setValue(programTm.getTeacherId());
        txtCost.setText(String.valueOf(programTm.getCost()));
    }

    public void clearTextField(){
        txtProgramName.clear();
        txtCost.setText("0.00");
        cmbTeachers.setValue(null);
        cmbTechnologies.setValue(null);
    }
    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
}
