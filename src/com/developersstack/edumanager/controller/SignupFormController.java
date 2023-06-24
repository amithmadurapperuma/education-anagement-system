package com.developersstack.edumanager.controller;

import com.developersstack.edumanager.db.Database;
import com.developersstack.edumanager.modle.User;
import com.developersstack.edumanager.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class SignupFormController {
    public AnchorPane context;
    public TextField txtFirstname;
    public TextField txtLastname;
    public TextField txtEmail;
    public PasswordField txtPassword;

    public void saveOnAction(ActionEvent actionEvent) throws IOException {
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String email = txtEmail.getText().toLowerCase();
        String password = new PasswordManager().encrypt(txtPassword.getText().trim());

        Database.userTable.add(new User(
                firstName, lastName, email, password
        ));

        new Alert(Alert.AlertType.INFORMATION, "New User Added!");
        setUi("LoginForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }

    public void exitOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
}
