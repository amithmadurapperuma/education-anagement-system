package com.developersstack.edumanager.controller;

import com.developersstack.edumanager.db.Database;
import com.developersstack.edumanager.modle.Intake;
import com.developersstack.edumanager.view.tm.IntakeTm;
import com.sun.org.apache.bcel.internal.generic.NEW;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class IntakeFormController {
    public AnchorPane context;
    public TextField txtIntakeId;
    public TextField txtIntakeName;
    public DatePicker txtStartDate;
    public Button btnSave;
    public TableView<IntakeTm> tblIntake;
    public TableColumn colIntakeId;
    public TableColumn colIntakeName;
    public TableColumn colIntakeStartDate;
    public TableColumn colAction;
    public TextField txtSearch;
    public String searchText = "";

    public void initialize() {
        colIntakeId.setCellValueFactory(new PropertyValueFactory<>("intakeId"));
        colIntakeName.setCellValueFactory(new PropertyValueFactory<>("intakeName"));
        colIntakeStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        setTableData(searchText);
        setIntakeId();

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setTableData(searchText);
        });

        tblIntake.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null != newValue){
                setTblValueToTextField(newValue);
            }
        });
    }

    public void backtoDashboardOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        if(btnSave.getText().equals("Save")){
            Intake intake = new Intake(
                    txtIntakeId.getText(),
                    txtIntakeName.getText(),
                    Date.from(txtStartDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    false
            );
            Database.intakeTable.add(intake);
            setTableData(searchText);
            clearTextField();
            setIntakeId();
        }else{
            for (Intake intake: Database.intakeTable) {
                if(intake.getIntakeId().equals(txtIntakeId.getText())){
                    intake.setIntakeName(txtIntakeName.getText());
                    intake.setStartDate(Date.from(txtStartDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    setTableData(searchText);
                    setIntakeId();
                    clearTextField();
                    btnSave.setText("Save");
                    return;
                }
                new Alert(Alert.AlertType.INFORMATION, "Record Not Found!").show();
            }
        }
    }

    public void newIntakeOnAction(ActionEvent actionEvent) {
        clearTextField();
        setIntakeId();
        btnSave.setText("Save");
    }

    private void setTableData(String searchText) {
        ObservableList<IntakeTm> obList = FXCollections.observableArrayList();
        for (Intake intake: Database.intakeTable) {
            if(intake.getIntakeName().contains(searchText)){
                Button btnDelete = new Button("Delete");
                IntakeTm intakeTm = new IntakeTm(
                        intake.getIntakeId(),
                        intake.getIntakeName(),
                        new SimpleDateFormat("yyyy-MM-dd").format(intake.getStartDate()),
                        btnDelete
                );
                btnDelete.setOnAction(e->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if(buttonType.get().equals(ButtonType.YES)){
                        Database.intakeTable.remove(intake);
                        setTableData(searchText);
                    }
                });
                obList.add(intakeTm);
            }
        }
        tblIntake.setItems(obList);
    }

    private void setIntakeId() {
        if(!Database.intakeTable.isEmpty()){
            Intake intakeIdIndex = Database.intakeTable.get(Database.intakeTable.size() - 1);
            String intakeId = intakeIdIndex.getIntakeId();
            String[] splitList = intakeId.split("-");
            String intakeIdIntegerAsString = splitList[1];
            int intakeIdIntegerAsInteger = Integer.parseInt(intakeIdIntegerAsString);
            intakeIdIntegerAsInteger++;
            txtIntakeId.setText("I-" + intakeIdIntegerAsInteger);
        }else {
            txtIntakeId.setText("I-1");
        }
    }

    public void setTblValueToTextField(IntakeTm intakeTm){
        txtIntakeId.setText(intakeTm.getIntakeId());
        txtIntakeName.setText(intakeTm.getIntakeName());
        txtStartDate.setValue(LocalDate.parse(intakeTm.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        btnSave.setText("Update");
    }
    public void clearTextField(){
        txtIntakeName.clear();
        txtStartDate.setValue(null);
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }

}
