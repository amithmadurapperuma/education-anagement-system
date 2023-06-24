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
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

public class PasswordResetFormController {
    public AnchorPane context;
    public String email;
    public TextField txtNewPassword;

    public void resetPasswordOnAction(ActionEvent actionEvent) throws IOException {
        Optional<User> selectedUser = Database.userTable.stream().filter(e -> e.getEmail().equals(email)).findFirst();
        if(selectedUser.isPresent()){
            for (User user: Database.userTable){
                user.setPassword(new PasswordManager().encrypt(txtNewPassword.getText()));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/LoginForm.fxml"));
                Parent parent = fxmlLoader.load();
                LoginFormController controller = fxmlLoader.getController();
                controller.txtUsername.setText(email);
                Stage stage = (Stage) context.getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.show();
            }
        }
    }
}
