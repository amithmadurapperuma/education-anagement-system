package com.developersstack.edumanager.controller;

import com.developersstack.edumanager.db.Database;
import com.developersstack.edumanager.modle.User;
import com.developersstack.edumanager.util.tools.SendEmail;
import com.developersstack.edumanager.util.tools.VerificationCodeGenerator;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class SendVerificationFormController {
    public AnchorPane context;
    public TextField txtEmail;

    public int generatedCode;

    public void initialize(){
    }
    public void sendEmailOnAction(ActionEvent actionEvent) throws IOException {
        Optional<User> selectedUser = Database.userTable.stream().filter(e -> e.getEmail().equals(txtEmail.getText())).findFirst();
        if(selectedUser.isPresent()){
            VerificationCodeGenerator code = new VerificationCodeGenerator();
            generatedCode = code.verificationCde();

            SendEmail sendEmail = new SendEmail(
                    txtEmail.getText(),
                    "VerificationCode",
                    "Hey " + selectedUser.get().getFirstName() + "\nYour Password Reset Code: " + generatedCode
            );

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CodeVerificationForm.fxml"));
            Parent parent = fxmlLoader.load();
            CodeVerificationFormController controller = fxmlLoader.getController();
            controller.generatedCode = generatedCode;
            controller.lblEmail.setText(txtEmail.getText());
            System.out.println(controller.generatedCode);
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
        }else {
            new Alert(Alert.AlertType.ERROR, "User Not Found!").show();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/LoginForm.fxml"));
            Parent parent = fxmlLoader.load();
            LoginFormController controller = fxmlLoader.getController();
            controller.txtUsername.setText(txtEmail.getText());
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

    public void setEmail(String email){
        txtEmail.setText(email);
    }

}
