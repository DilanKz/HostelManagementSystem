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
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostelManagement.dto.RoomDTO;

public class RoomFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private TableView<RoomDTO> tblRooms;

    @FXML
    private TableColumn<RoomDTO, String> colTypeID;

    @FXML
    private TableColumn<RoomDTO, String> colType;

    @FXML
    private TableColumn<RoomDTO, String> colKeyMoney;

    @FXML
    private TableColumn<RoomDTO, String> colQty;

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
        setProperties();
    }

    private void setProperties(){
        colTypeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
}
