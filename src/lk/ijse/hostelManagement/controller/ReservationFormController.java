package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.ReservationBO;
import lk.ijse.hostelManagement.dto.ReservationDTO;
import lk.ijse.hostelManagement.dto.RoomDTO;
import lk.ijse.hostelManagement.dto.StudentDTO;

public class ReservationFormController {

    public JFXButton btnClear;
    public JFXButton btnCancel;
    public JFXButton btnNewRes;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private Label lblStudentName;

    @FXML
    private ComboBox<String> cmbGender1;

    @FXML
    private Label lblRoomQty;

    @FXML
    private JFXCheckBox cbxStatus;

    @FXML
    private JFXButton btnReserve;

    @FXML
    private TableView<?> tblStudentDetails;

    @FXML
    private TableColumn<?, ?> colReserveID;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private TableColumn<?, ?> colSName;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colRoomID;

    @FXML
    private TableColumn<?, ?> colRoomStatus;

    @FXML
    private Label lblResId;

    @FXML
    private TableView<ReservationDTO> tblResDetails;

    @FXML
    private TableColumn<ReservationDTO, String> colResID;

    @FXML
    private TableColumn<ReservationDTO, String> colDate;

    @FXML
    private TableColumn<ReservationDTO, String> colStudent;

    @FXML
    private TableColumn<ReservationDTO, String> colRoomTID;

    @FXML
    private TableColumn<ReservationDTO, String> colStatus;
    private ReservationBO reservationBO= (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Reservation);
    private StudentDTO studentData;
    private RoomDTO roomData;

    @FXML
    void btnAddNewReservationOnAction(ActionEvent event) throws Exception {
        btnNewRes.setDisable(true);
        setReserveID();
        setFieldStatus(false);
        setIds();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
    }

    @FXML
    void btnReserveOnAction(ActionEvent event) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        setFieldStatus(true);
        btnNewRes.setDisable(false);
        clearFields();
    }

    @FXML
    void initialize() {
        setCellFactory();
    }

    private void setCellFactory(){
        colReserveID.setCellValueFactory(new PropertyValueFactory<>("resID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colRoomTID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void setReserveID() throws Exception {
        lblResId.setText(reservationBO.generateNextReservationID());
    }

    private void setFieldStatus(boolean status){
        cmbGender.setDisable(status);
        cmbGender1.setDisable(status);
        btnReserve.setDisable(status);
        btnClear.setDisable(status);
        btnCancel.setDisable(status);
        cbxStatus.setDisable(status);
    }

    private void setIds() throws Exception{
        List<String> studentIds = reservationBO.getStudentIds();
        ObservableList<String> studentList= FXCollections.observableList(studentIds);
        cmbGender.setItems(studentList);

        List<String> roomIds = reservationBO.getRoomIds();
        ObservableList<String> roomList=FXCollections.observableList(roomIds);
        cmbGender1.setItems(roomList);

    }
    private StudentDTO getStudentData() throws Exception {
        String studentID = cmbGender.getSelectionModel().getSelectedItem();
        return reservationBO.getStudent(studentID);
    }

    private RoomDTO getRoomData() throws Exception {
        String roomID = cmbGender1.getSelectionModel().getSelectedItem();
        return reservationBO.getRoom(roomID);
    }
    public void cmbStudentOnAction(ActionEvent actionEvent) throws Exception {
        studentData = getStudentData();
        lblStudentName.setText(studentData.getName());
    }

    public void cmbRoomOnAction(ActionEvent actionEvent) throws Exception {
        roomData = getRoomData();
        lblRoomQty.setText(String.valueOf(roomData.getQty()));
    }

    private void clearFields(){
        /*lblStudentName.setText(" ");
        lblResId.setText(" ");
        lblRoomQty.setText(" ");
        cmbGender.getSelectionModel().clearSelection();
        cmbGender1.getSelectionModel().clearSelection();*/
    }

    private ReservationDTO getData(){

        String status="unPaid";
        if (cbxStatus.isSelected()){
            status="paid";
        }

        String date= String.valueOf(LocalDate.now());

        return new ReservationDTO();
    }
}
