package com.developersstack.edumanager.controller;

import com.developersstack.edumanager.db.Database;
import com.developersstack.edumanager.modle.User;
import com.developersstack.edumanager.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Optional;

public class LoginFormController {
    public TextField txtUsername;
    public TextField txtPassword;
    public AnchorPane context;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtUsername.getText().toLowerCase();
        String password = txtPassword.getText().trim();

      /*  for(User user: Database.userTable){
            if(user.getEmail().equals(email)){
                if(user.getPassword().equals(password)){
                    setUi("Dashboard");
                }
                new Alert(Alert.AlertType.WARNING, "Invalid Password!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, String.format("User Not Found (%s)", email)).show();
            }
        }*/
        Optional<User> selectedUser = Database.userTable.stream().filter(e-> e.getEmail().equals(email)).findFirst();
        if(selectedUser.isPresent()){
            if(new PasswordManager().checkPassword(password, selectedUser.get().getPassword())){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/DashboardForm.fxml"));
                Parent parent = fxmlLoader.load();
                DashboardFormController controller = fxmlLoader.getController();
                controller.txtLoginUser.setText(Database.userTable.stream().filter(e->e.getEmail().
                        equals(txtUsername.getText())).findFirst().get().getFirstName().
                        concat( " ").concat(Database.userTable.stream().filter(e->e.getEmail().equals(txtUsername.getText())).
                                findFirst().get().getLastName()));
                Stage stage = (Stage) context.getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.centerOnScreen();
            }else{
                new Alert(Alert.AlertType.ERROR,"Wrong Password").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, String.format("User Not Found (%s)", email)).show();
        }
    }

    public void signupOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignupForm");
    }

    public void fogetPassOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SendVerificationForm.fxml"));
        Parent parent = fxmlLoader.load();
        SendVerificationFormController controller = fxmlLoader.getController();
        controller.setEmail(txtUsername.getText());
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(parent));
//        setUi("SendVerificationForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
}