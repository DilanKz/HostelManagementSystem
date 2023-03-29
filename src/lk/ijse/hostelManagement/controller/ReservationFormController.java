package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ReservationFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> cmbGender;

    @FXML
    private Label lblStudentName;

    @FXML
    private ComboBox<?> cmbGender1;

    @FXML
    private Label lblRoomQty;

    @FXML
    private JFXCheckBox cbxStatus;

    @FXML
    private JFXButton btnReserve;

    @FXML
    void btnAddNewReservationOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnReserveOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
    }
}
