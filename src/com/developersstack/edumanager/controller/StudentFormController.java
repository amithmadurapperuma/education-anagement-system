package com.developersstack.edumanager.controller;

import com.developersstack.edumanager.db.Database;
import com.developersstack.edumanager.modle.Student;
import com.developersstack.edumanager.view.tm.StudentTm;
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

public class StudentFormController {
    public AnchorPane context;
    public TextField txtStudentId;
    public TextField txtFirstname;
    public TextField txtLastname;
    public DatePicker txtDOB;
    public TextField txtAddress;
    public TextField txtSearch;
    public TableView<StudentTm> tblStudent;
    public TableColumn colId;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colDOB;
    public TableColumn colAddress;
    public TableColumn colActionBtn;
    public Button btnUpdate;
    public Button btnSaveUser;
    public String searchText = "";


    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colActionBtn.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        setStudentId();
        setTableData(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setTableData(searchText);
        });

        tblStudent.getSelectionModel().selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    if(null != newValue){
                        setData(newValue);
                    }
        }));
    }

    private void setData(StudentTm studentTm) {
        txtStudentId.setText(studentTm.getStudentId());
        txtFirstname.setText(studentTm.getFirstName());
        txtLastname.setText(studentTm.getLastName());
        txtDOB.setValue(LocalDate.parse(studentTm.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        txtAddress.setText(studentTm.getAddress());
        btnUpdate.setDisable(false);
        btnSaveUser.setDisable(true);
    }

    public void saveOnAction(ActionEvent actionEvent) {
        Student student = new Student(
                txtStudentId.getText(),
                txtFirstname.getText(),
                txtLastname.getText(),
                Date.from(txtDOB.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                txtAddress.getText()
        );
        Database.studentTable.add(student);
        setTableData(searchText);
        setStudentId();
        clearTextField();
        new Alert(Alert.AlertType.INFORMATION, "New Student Saved!").show();
    }

    public void updateOnAction(ActionEvent actionEvent) {
        for (Student student: Database.studentTable) {
                if(student.getStudentId().equals(txtStudentId.getText())){
                    student.setFirstName(txtFirstname.getText());
                    student.setLastName(txtLastname.getText());
                    student.setDateOfBirth(Date.from(txtDOB.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    student.setAddress(txtAddress.getText());
                    setTableData(searchText);
                    setStudentId();
                    clearTextField();
                    btnUpdate.setDisable(true);
                    btnSaveUser.setDisable(false);
                    return;
                }
            new Alert(Alert.AlertType.WARNING, "Record not Found!").show();
        }
    }

    public void newStudentOnAction(ActionEvent actionEvent) {
        clearTextField();
        setStudentId();
        btnUpdate.setDisable(true);
        btnSaveUser.setDisable(false);
    }
    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }

    public void setStudentId(){
        if(!Database.studentTable.isEmpty()){
            Student lastStudentIndex = Database.studentTable.get(Database.studentTable.size() - 1);
            String lastStudentId = lastStudentIndex.getStudentId();
            String[] splitData = lastStudentId.split("-");
            String lastStudentIdIntegerAsString = splitData[1];
            int lastStudentIdIntegerAsInteger = Integer.parseInt(lastStudentIdIntegerAsString);
            lastStudentIdIntegerAsInteger++;
            txtStudentId.setText("S-" + lastStudentIdIntegerAsInteger);
        }else {
            txtStudentId.setText("S-1");
        }
    }

    public void setTableData(String searchText){
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();
        for (Student student: Database.studentTable) {
            if(student.getFirstName().contains(searchText)){
                Button btnAction = new Button("Delete");
                StudentTm studentTm = new StudentTm(
                        student.getStudentId(),
                        student.getFirstName(),
                        student.getLastName(),
                        new SimpleDateFormat("yyyy-MM-dd").format(student.getDateOfBirth()),
                        student.getAddress(),
                        btnAction
                );
                btnAction.setOnAction(e->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if(buttonType.get().equals(ButtonType.YES)){
                        Database.studentTable.remove(student);
                        new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                        setTableData(searchText);
                    }
                });
                obList.add(studentTm);
            }
        }
        tblStudent.setItems(obList);
    }

    public void clearTextField(){
        txtFirstname.clear();
        txtLastname.clear();
        txtAddress.clear();
        txtDOB.setValue(null);
    }
}
