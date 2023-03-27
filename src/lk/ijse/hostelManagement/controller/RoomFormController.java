package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RoomFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private TableView<?> tblRooms;

    @FXML
    private TableColumn<?, ?> colTypeID;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQty;

    @FXML
    private Label lblStudentID;

    @FXML
    void btnAddNewStudentOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'RoomForm.fxml'.";
        assert tblRooms != null : "fx:id=\"tblRooms\" was not injected: check your FXML file 'RoomForm.fxml'.";
        assert colTypeID != null : "fx:id=\"colTypeID\" was not injected: check your FXML file 'RoomForm.fxml'.";
        assert colType != null : "fx:id=\"colType\" was not injected: check your FXML file 'RoomForm.fxml'.";
        assert colKeyMoney != null : "fx:id=\"colKeyMoney\" was not injected: check your FXML file 'RoomForm.fxml'.";
        assert colQty != null : "fx:id=\"colQty\" was not injected: check your FXML file 'RoomForm.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'RoomForm.fxml'.";
        assert txtType != null : "fx:id=\"txtType\" was not injected: check your FXML file 'RoomForm.fxml'.";
        assert txtKeyMoney != null : "fx:id=\"txtKeyMoney\" was not injected: check your FXML file 'RoomForm.fxml'.";
        assert txtQty != null : "fx:id=\"txtQty\" was not injected: check your FXML file 'RoomForm.fxml'.";
        assert lblStudentID != null : "fx:id=\"lblStudentID\" was not injected: check your FXML file 'RoomForm.fxml'.";

    }
}
