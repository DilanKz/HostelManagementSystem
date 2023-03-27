package lk.ijse.hostelManagement.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainFormController {

    public AnchorPane scenePane;

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
    void btnReservationOnAction(ActionEvent event) {

    }

    @FXML
    void btnRoomsOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert lblFormName != null : "fx:id=\"lblFormName\" was not injected: check your FXML file 'MainForm.fxml'.";
        assert lblDate != null : "fx:id=\"lblDate\" was not injected: check your FXML file 'MainForm.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'MainForm.fxml'.";

    }
}
