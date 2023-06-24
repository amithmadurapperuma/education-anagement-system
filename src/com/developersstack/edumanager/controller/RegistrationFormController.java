package com.developersstack.edumanager.controller;

import com.developersstack.edumanager.db.Database;
import com.developersstack.edumanager.modle.Program;
import com.developersstack.edumanager.modle.Register;
import com.developersstack.edumanager.modle.Student;
import com.developersstack.edumanager.view.tm.RegisterTm;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

public class RegistrationFormController {
    public AnchorPane context;
    public TextField txtRegId;
    public ComboBox<String> cmbStudent;
    public ComboBox<String> cmbProgram;
    public DatePicker txtRegDate;
    public TableView<RegisterTm> tblRegister;
    public TableColumn colRegId;
    public TableColumn colStudent;
    public TableColumn colProgram;
    public TableColumn colRegDate;
    public TableColumn colAction;
    public TextField txtSearch;
    public String searchText = "";
    public Button btnRegister;
    public RadioButton rdbtnPaymentPaid;
    public RadioButton rdbtnPaymentPending;
    public TableColumn colPayment;

    public void initialize(){
        setRegId();
        setStudent();
        setProgram();
        setTblRegisterData(searchText);

        colRegId.setCellValueFactory(new PropertyValueFactory<>("regId"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("student"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            txtSearch.setText(newValue);
            setTblRegisterData(searchText);
        });

        tblRegister.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null != newValue){
                setTblDataToTextField(newValue);
            }
        });
    }
    public void btnBacktoDashboardOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void RegOnAction(ActionEvent actionEvent) {
        if(btnRegister.getText().equals("Register")){
            Register register = new Register(
                    txtRegId.getText(),
                    cmbStudent.getValue(),
                    cmbProgram.getValue(),
                    Date.from(txtRegDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    rdbtnPaymentPaid.isSelected()
            );
            Database.registerTable.add(register);
            } else if (btnRegister.getText().equals("Update")) {
            Optional<Register> selectedUser = Database.registerTable.stream().filter(e -> e.getRegId().equals(txtRegId.getText())).findFirst();
            if (selectedUser.isPresent()){
                selectedUser.get().setStudent(cmbStudent.getValue());
                selectedUser.get().setProgram(cmbProgram.getValue());
                selectedUser.get().setRegDate(Date.from(txtRegDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                selectedUser.get().setPayment(rdbtnPaymentPaid.isSelected());
            }else {
                new Alert(Alert.AlertType.INFORMATION, "Not Found!").show();
            }
            btnRegister.setText("Save");
        }
        setTblRegisterData(searchText);
        clearTextField();
        setRegId();
    }

    public void newRegOnAction(ActionEvent actionEvent) {
        clearTextField();
    }

    public void setRegId(){
        if(!Database.registerTable.isEmpty()){
            String[] lastRegIdInt = Database.registerTable.get(Database.registerTable.size() - 1).
                    getRegId().split("-");
            String lastRegIdIntAsStr = lastRegIdInt[1];
            int lastRegIdIntAsInt = Integer.parseInt(lastRegIdIntAsStr);
            lastRegIdIntAsInt++;
            txtRegId.setText("R-" + lastRegIdIntAsInt);
        }else{
            txtRegId.setText("R-1");
        }
    }
    public void setStudent(){
        ArrayList<String> studentArr = new ArrayList<>();
        for (Student student: Database.studentTable) {
            studentArr.add(student.getFirstName());
        }
        ObservableList<String> studentObList = FXCollections.observableArrayList(studentArr);
        cmbStudent.setItems(studentObList);
    }

    public void setProgram(){
        ObservableList<String> programObList = FXCollections.observableArrayList();
        for (Program program: Database.programTable) {
            programObList.add(program.getProgramName());
        }
        cmbProgram.setItems(programObList);
    }

    public void setTblRegisterData(String searchText){
        ObservableList<RegisterTm> regStdObList = FXCollections.observableArrayList();
        for (Register register: Database.registerTable) {
            if(txtSearch.getText().contains(searchText)){
                Button btnDelete = new Button("Delete");
                RegisterTm registerTm = new RegisterTm(
                        register.getRegId(),
                        register.getStudent(),
                        register.getProgram(),
                        new SimpleDateFormat("yyyy-MM-dd").format(register.getRegDate()),
                        register.getPayment(),
                        btnDelete
                );
                btnDelete.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sur?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if(buttonType.get().equals(ButtonType.YES)){
                        Database.registerTable.remove(register);
                        setTblRegisterData(searchText);
                    }
                });
                regStdObList.add(registerTm);
            }
        }
        tblRegister.setItems(regStdObList);
    }

    public void setTblDataToTextField(RegisterTm registerTm){
        txtRegId.setText(registerTm.getRegId());
        cmbStudent.setValue(registerTm.getStudent());
        cmbProgram.setValue(registerTm.getProgram());
        txtRegDate.setValue(LocalDate.parse(registerTm.getRegDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if(registerTm.getPayment().equals(true)){
           rdbtnPaymentPaid.fire();
        }else{
            rdbtnPaymentPending.fire();
        }
        btnRegister.setText("Update");
    }
    public void clearTextField(){
        txtRegDate.setValue(null);
        cmbProgram.setValue(null);
        cmbStudent.setValue(null);
    }
    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
}