package lk.ijse.hostelManagement.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StudentFormController {

    public JFXButton btnAdd;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> tblStudents;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<?> cmbGender;

    @FXML
    private Label lblStudentID;

    @FXML
    private TextField txtSearch;

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
        assert tblStudents != null : "fx:id=\"tblStudents\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert colID != null : "fx:id=\"colID\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert colName != null : "fx:id=\"colName\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert colAddress != null : "fx:id=\"colAddress\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert colContact != null : "fx:id=\"colContact\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert colDOB != null : "fx:id=\"colDOB\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert colGender != null : "fx:id=\"colGender\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert txtContact != null : "fx:id=\"txtConact\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert txtDOB != null : "fx:id=\"txtDOB\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert cmbGender != null : "fx:id=\"cmbGender\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert lblStudentID != null : "fx:id=\"lblStudentID\" was not injected: check your FXML file 'StudentForm.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'StudentForm.fxml'.";

    }

    public void btnAddNewStudentOnAction(ActionEvent actionEvent) {
    }
}

