package com.developersstack.edumanager.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DashboardFormController {
    public AnchorPane context;
    public Label lblDate;
    public Label lblTime;
    public Label txtLoginUser;

    public void initialize(){
        setDate();
    }
    public void studentOnAction(ActionEvent actionEvent) throws IOException {
        setUi("StudentForm");
    }

    public void teachersOnAction(ActionEvent actionEvent) throws IOException {
        setUi("TeachersForm");
    }

    public void intakeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("IntakeForm");
    }

    public void programOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ProgramForm");
    }

    public void registrationOnAction(ActionEvent actionEvent) throws IOException {
        setUi("RegistrationForm");
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }

    public void setDate(){
        /*Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        lblDate.setText(dateFormat.format(date));
        lblTime.setText(timeFormat.format(date));*/
        lblDate.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        e->{
                            DateTimeFormatter dateTimeFormatter =
                                    DateTimeFormatter.ofPattern("hh:mm:ss");
                            lblTime.setText(LocalTime.now().format(dateTimeFormatter));
                        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
}
