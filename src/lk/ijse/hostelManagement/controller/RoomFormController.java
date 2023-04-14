package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.RoomsBO;
import lk.ijse.hostelManagement.dto.RoomDTO;
import lk.ijse.hostelManagement.dto.StudentDTO;

public class RoomFormController {

    public JFXButton btnClear;
    public JFXButton btnDelete;
    public JFXButton btnCancel;
    public JFXButton btnAddRoom;
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

    RoomsBO roomsBO= (RoomsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Rooms);

    @FXML
    void btnAddNewStudentOnAction(ActionEvent event) throws Exception {
        btnAdd.setText("Save");
        btnAddRoom.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        setFieldsActivation(false,false);
        clearFields();
        setID();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception{
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isSaved = roomsBO.deleteRoom(
                    new RoomDTO(
                            lblStudentID.getText(),
                            txtType.getText(),
                            txtKeyMoney.getText(),
                            Integer.parseInt(txtQty.getText())
                    )
            );

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Deleted").show();
                setFieldsActivation(true,false);
                clearFields();
                tblRooms.getItems().clear();
                loadAllRooms();
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception{
        if (btnAdd.getText().equals("Save")){
            saveRoom();
        }else if (btnAdd.getText().equals("Update")){
            updateRoom();
        }
    }

    private void saveRoom() throws Exception {
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isSaved = roomsBO.saveRoom(
                    new RoomDTO(
                            lblStudentID.getText(),
                            txtType.getText(),
                            txtKeyMoney.getText(),
                            Integer.parseInt(txtQty.getText())
                    )
            );

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Room saved").show();
                setFieldsActivation(true,false);
                clearFields();
                tblRooms.getItems().clear();
                loadAllRooms();
                btnCancel.setDisable(true);
                btnAddRoom.setDisable(false);
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    private void updateRoom() throws Exception {
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isSaved = roomsBO.updateRoom(
                    new RoomDTO(
                            lblStudentID.getText(),
                            txtType.getText(),
                            txtKeyMoney.getText(),
                            Integer.parseInt(txtQty.getText())
                    )
            );

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved").show();
                setFieldsActivation(true,false);
                clearFields();
                tblRooms.getItems().clear();
                loadAllRooms();
                btnCancel.setDisable(true);
                btnAddRoom.setDisable(false);
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    @FXML
    void initialize() throws Exception{
        setProperties();
        loadAllRooms();
        getData();
    }

    private void setProperties(){
        colTypeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    void loadAllRooms() throws Exception{
        List<RoomDTO> roomDTOS = roomsBO.loadAll();
        ObservableList<RoomDTO> observableList= FXCollections.observableList(roomDTOS);
        tblRooms.setItems(observableList);
    }

    private boolean checkValidation(){
        String typeText = txtType.getText();
        String moneyText = txtKeyMoney.getText();
        String qtyText = txtQty.getText();

        if (!typeText.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtType.requestFocus();
            return false;
        } else if (!moneyText.matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "invalid key money").show();
            txtKeyMoney.requestFocus();
            return false;
        } else if (!qtyText.matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtQty.requestFocus();
            return false;
        }else {
            return true;
        }

    }

    private void clearFields(){
        lblStudentID.setText(" ");
        txtQty.clear();
        txtKeyMoney.clear();
        txtType.clear();
    }

    private void setID() throws Exception {
        lblStudentID.setText(roomsBO.generateNextRoomID());
    }

    void getData(){
        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setFieldsActivation(false,true);
            btnCancel.setDisable(true);

            btnAdd.setText(newValue != null ? "Update" : "Save");
            btnAdd.setDisable(newValue == null);

            if (newValue != null) {

                boolean isHave = roomsBO.getCount(newValue.getId());
                if (isHave){
                    btnDelete.setVisible(true);
                }else {
                    btnDelete.setVisible(false);
                }

                lblStudentID.setText(newValue.getId());
                txtType.setText(newValue.getType());
                txtKeyMoney.setText(newValue.getKeyMoney());
                txtQty.setText(String.valueOf(newValue.getQty()));

            }
        });
    }

    @FXML
    private void btnCancelOnAction(ActionEvent actionEvent) {
        btnCancel.setDisable(true);
        btnAddRoom.setDisable(false);
        clearFields();
        setFieldsActivation(true,false);
    }

    private void setFieldsActivation(boolean isActivate,boolean isStatus){

        txtType.setDisable(isActivate);
        txtKeyMoney.setDisable(isActivate);
        txtQty.setDisable(isActivate);

        btnClear.setDisable(isActivate);
        btnAdd.setDisable(isActivate);

        if (!isActivate){
            if (isStatus){
                btnDelete.setDisable(false);
                btnAdd.setText("Update");
            }
        }
    }
}
