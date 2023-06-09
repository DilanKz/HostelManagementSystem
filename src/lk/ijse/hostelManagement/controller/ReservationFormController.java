package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.ReservationBO;
import lk.ijse.hostelManagement.dto.ReservationDTO;
import lk.ijse.hostelManagement.dto.RoomDTO;
import lk.ijse.hostelManagement.dto.StudentDTO;
import lk.ijse.hostelManagement.projection.StudentDetailsDTO;

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
    private TableView<StudentDetailsDTO> tblStudentDetails;

    @FXML
    private TableColumn<StudentDetailsDTO, String> colReserveID;

    @FXML
    private TableColumn<StudentDetailsDTO, String> colStudentID;

    @FXML
    private TableColumn<StudentDetailsDTO, String> colSName;

    @FXML
    private TableColumn<StudentDetailsDTO, String> colContact;

    @FXML
    private TableColumn<StudentDetailsDTO, String> colRoomID;

    @FXML
    private TableColumn<StudentDetailsDTO, Date> colRoomStatus;

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
    private String id;
    @FXML
    void btnAddNewReservationOnAction(ActionEvent event) throws Exception {
        btnReserve.setText("Reserve");
        btnNewRes.setDisable(true);
        setReserveID();
        setFieldStatus(false);
        setIds();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnReserveOnAction(ActionEvent event) throws Exception {
        if (btnReserve.getText().equals("Reserve")){
            boolean isSaved = reserveARoom(getData());
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Reserved").show();
                tblResDetails.getItems().clear();
                tblStudentDetails.getItems().clear();

                loadAll();
                setUnpaidDetails();

                btnReserve.setDisable(true);
                btnClear.setDisable(true);
                btnCancel.setDisable(true);
                btnNewRes.setDisable(false);

                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        }else {
            if (cbxStatus.isSelected()){
                btnReserve.setDisable(false);

                String status="paid";

                boolean isUpdated = reservationBO.changePaidStatus(id, status);
                if (isUpdated){
                    clearFields();
                    tblResDetails.getItems().clear();
                    tblStudentDetails.getItems().clear();

                    new Alert(Alert.AlertType.CONFIRMATION, "Status updated").show();

                    loadAll();
                    setUnpaidDetails();

                    cbxStatus.setDisable(true);
                    btnReserve.setDisable(true);
                }else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }

            }else if (!cbxStatus.isSelected())btnReserve.setDisable(true);
        }

    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        setFieldStatus(true);
        btnNewRes.setDisable(false);
        clearFields();
    }

    @FXML
    void initialize() throws Exception{
        setCellFactory();
        loadAll();
        setUnpaidDetails();
        getUnpaidStudent();
    }

    private void setCellFactory(){
        colResID.setCellValueFactory(new PropertyValueFactory<>("resID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudent.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colRoomTID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colReserveID.setCellValueFactory(new PropertyValueFactory<>("resID"));
        colRoomStatus.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colRoomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadAll() throws Exception {
        List<ReservationDTO> reservationDTOS = reservationBO.loadAll();
        ObservableList<ReservationDTO> dtoObservableList=FXCollections.observableList(reservationDTOS);

        tblResDetails.setItems(dtoObservableList);
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
        if (cmbGender.getSelectionModel().getSelectedItem()!=null){
            StudentDTO studentData= getStudentData();
            lblStudentName.setText(studentData.getName());
        }
    }

    public void cmbRoomOnAction(ActionEvent actionEvent) throws Exception {
       if (cmbGender1.getSelectionModel().getSelectedItem()!=null){
           RoomDTO roomData = getRoomData();
           lblRoomQty.setText(String.valueOf(roomData.getQty()));
       }
    }

    private void clearFields(){
        lblStudentName.setText(" ");
        lblResId.setText(" ");
        lblRoomQty.setText(" ");
        cmbGender.getSelectionModel().clearSelection();
        cmbGender1.getSelectionModel().clearSelection();
    }

    private ReservationDTO getData() throws Exception {

        String status="unPaid";
        if (cbxStatus.isSelected()){
            status="paid";
        }

        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        StudentDTO studentData= getStudentData();
        RoomDTO roomData=getRoomData();

        return new ReservationDTO(
                lblResId.getText(),
                sqlDate,
                studentData,
                roomData,
                status
        );


    }

    private boolean reserveARoom(ReservationDTO reservationDTO) throws Exception {
        boolean isSaved = reservationBO.saveReservation(reservationDTO);

        if (!isSaved){
            return false;
        }

        RoomDTO room = reservationDTO.getRoom();
        room.setQty(room.getQty()-1);

        boolean isUpdated = reservationBO.updateRoomQty(room);

        if (!isUpdated){
            return false;
        }


        return true;
    }

    private void setUnpaidDetails(){
        List<StudentDetailsDTO> unpaidStudents = reservationBO.getUnpaidStudents();
        ObservableList<StudentDetailsDTO> studentDetailsDTOS=FXCollections.observableList(unpaidStudents);

        tblStudentDetails.setItems(studentDetailsDTOS);
    }

    void getUnpaidStudent(){
        tblStudentDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnCancel.setDisable(true);

            btnReserve.setText(newValue != null ? "Update" : "Reserve");
            //btnReserve.setDisable(newValue == null);

            if (newValue != null) {
                cbxStatus.setDisable(false);
                id= newValue.getResID();
                lblStudentName.setText(newValue.getName());
                cmbGender.getSelectionModel().select(newValue.getStudentID());
                cmbGender1.getSelectionModel().select(newValue.getRoomID());
                lblResId.setText(newValue.getResID());
            }
        });
    }

    public void cbxStatusOnAction(ActionEvent actionEvent) {
        if (btnReserve.getText().equals("Update")){
            if (cbxStatus.isSelected()){
                btnReserve.setDisable(false);

                String status="paid";

            }else if (!cbxStatus.isSelected())btnReserve.setDisable(true);
        }
    }
}
