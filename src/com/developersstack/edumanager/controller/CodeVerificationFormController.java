package com.developersstack.edumanager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CodeVerificationFormController {
    public AnchorPane context;
    public Label lblEmail;
    public TextField txtVerificationCode;
    public int generatedCode;
    public Label lblCode;

    public void initialize(){

    }
    public void verifyOnAction(ActionEvent actionEvent) throws IOException {
        if(txtVerificationCode.getText().equals(String.valueOf(generatedCode))){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/PasswordResetForm.fxml"));
            Parent parent = fxmlLoader.load();
            PasswordResetFormController controller = fxmlLoader.getController();
            controller.email = lblEmail.getText();
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SendVerificationForm.fxml"));
            Parent parent = fxmlLoader.load();
            SendVerificationFormController controller = fxmlLoader.getController();
            controller.txtEmail.setText(lblEmail.getText());
            Stage stage = (Stage) context.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

    public void setUi(String location) throws IOException {
       /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/" + location + "fxml"));
        Parent parent = fxmlLoader.load();
        PasswordResetFormController controller = fxmlLoader.getController();
        controller.email = lblEmail.getText();
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();*/
    }
}
