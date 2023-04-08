package lk.ijse.hostelManagement.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hostelManagement.util.Navigation;

public class MainFormController {

    public AnchorPane scenePane;
    public JFXButton btnStudent;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblFormName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Navigation.Routes.Reservation,scenePane);
    }

    @FXML
    void btnRoomsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Navigation.Routes.Rooms,scenePane);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        //Navigation.navigate(Navigation.Routes.Student,scenePane);
        scenePane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/hostelManagement/view/StudentForm.fxml")));

    }


    @FXML
    void btnUserSettingsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();

        Parent window = FXMLLoader.load(this.getClass().getResource("../view/UserSettingsForm.fxml"));
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.initOwner(Navigation.stage);
        stage.setTitle("User Setting");
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.stage.close();

        Stage stage=new Stage();
        Parent window = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void initialize() {
        setDateAndTime();
        btnStudent.fire();
    }

    private void setDateAndTime(){
        lblDate.setText(String.valueOf(LocalDate.now()));

        //setting Animation time
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
