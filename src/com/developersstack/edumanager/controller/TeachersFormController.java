package com.developersstack.edumanager.controller;

import com.developersstack.edumanager.db.Database;
import com.developersstack.edumanager.modle.Teacher;
import com.developersstack.edumanager.view.tm.TeacherTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class TeachersFormController {
    public AnchorPane context;
    public TextField txtTeacherId;
    public TextField txtFirstname;
    public TextField txtLastname;
    public TextField txtContact;
    public TextField txtAddress;
    public Button btnSaveTeacher;
    public Button btnUpdateTeacher;
    public TextField txtSearch;
    public TableView<TeacherTm> tblData;
    public TableColumn colId;
    public TableColumn colFirstname;
    public TableColumn colLastname;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colAction;
    public String searchText = "";

    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        setTableData(searchText);
        setTeacherId();

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setTableData(searchText);
        });

        tblData.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if(null != newValue){
                setTblDataToTxtField(newValue);
            }
        }));
    }
    public void backtoDashboardOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        Teacher teacher = new Teacher(
                txtTeacherId.getText(),
                txtFirstname.getText(),
                txtLastname.getText(),
                txtContact.getText(),
                txtAddress.getText()
        );
        Database.teacherTable.add(teacher);
        setTableData(searchText);
        setTeacherId();
        clearTextField();
        new Alert(Alert.AlertType.INFORMATION, "New Teacher Added!").show();
    }

    public void newStudentOnAction(ActionEvent actionEvent) {
        clearTextField();
        setTeacherId();
        btnSaveTeacher.setDisable(false);
        btnUpdateTeacher.setDisable(true);
    }

    public void updateOnAction(ActionEvent actionEvent) {
        for (Teacher teacher: Database.teacherTable) {
            if(teacher.getTeacherId().equals(txtTeacherId.getText())){
                teacher.setFirstName(txtFirstname.getText());
                teacher.setLastName(txtLastname.getText());
                teacher.setContact(txtContact.getText());
                teacher.setAddress(txtAddress.getText());
                setTableData(searchText);
                setTeacherId();
                clearTextField();
                btnUpdateTeacher.setDisable(true);
                btnSaveTeacher.setDisable(false);
                return;
            }
            new Alert(Alert.AlertType.WARNING,"Record not Found");
        }
    }

    public void setTeacherId(){
        if(!Database.teacherTable.isEmpty()){
            Teacher lastIdIndex = Database.teacherTable.get(Database.teacherTable.size() - 1);
            String lastId = lastIdIndex.getTeacherId();
            String[] splitData = lastId.split("-");
            String lastIdIntegerAsString = splitData[1];
            int lastIdIntegerAsInteger = Integer.parseInt(lastIdIntegerAsString);
            lastIdIntegerAsInteger++;
            txtTeacherId.setText("T-" + lastIdIntegerAsInteger);
        }else{
            txtTeacherId.setText("T-1");
        }
    }

    public void clearTextField(){
        txtFirstname.clear();
        txtLastname.clear();
        txtContact.clear();
        txtAddress.clear();
    }

    public void setTableData(String searchText){
        ObservableList<TeacherTm> obsList = FXCollections.observableArrayList();
        for (Teacher teacher: Database.teacherTable) {
            if(teacher.getFirstName().contains(searchText)){
                Button btnDelete = new Button("Delete");
                TeacherTm teacherTm = new TeacherTm(
                        teacher.getTeacherId(),
                        teacher.getFirstName(),
                        teacher.getLastName(),
                        teacher.getContact(),
                        teacher.getAddress(),
                        btnDelete
                );
                btnDelete.setOnAction(e->{
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure!"
                           , ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if(buttonType.get().equals(ButtonType.YES)){
                        Database.teacherTable.remove(teacher);
                        new Alert(Alert.AlertType.INFORMATION,"Deleted").show();
                        setTableData(searchText);
                    }
                });
                obsList.add(teacherTm);
            }
        }
        tblData.setItems(obsList);
    }

    public void setTblDataToTxtField(TeacherTm teacherTm){
        txtTeacherId.setText(teacherTm.getTeacherId());
        txtFirstname.setText(teacherTm.getFirstName());
        txtLastname.setText(teacherTm.getLastName());
        txtContact.setText(teacherTm.getContact());
        txtAddress.setText(teacherTm.getAddress());
        btnSaveTeacher.setDisable(true);
        btnUpdateTeacher.setDisable(false);
    }
    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
}
