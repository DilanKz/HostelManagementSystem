package lk.ijse.hostelManagement.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.StudentBO;
import lk.ijse.hostelManagement.dto.StudentDTO;

public class StudentFormController {

    public JFXButton btnAdd;
    public JFXButton btnClear;
    public JFXButton btnDelete;
    public JFXButton btnAddStudent;
    public JFXButton btnCancel;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StudentDTO> tblStudents;

    @FXML
    private TableColumn<StudentDTO, String> colID;

    @FXML
    private TableColumn<StudentDTO, String> colName;

    @FXML
    private TableColumn<StudentDTO, String> colAddress;

    @FXML
    private TableColumn<StudentDTO, String> colContact;

    @FXML
    private TableColumn<StudentDTO, String> colDOB;

    @FXML
    private TableColumn<StudentDTO, String> colGender;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private Label lblStudentID;

    @FXML
    private TextField txtSearch;
    private StudentBO studentBO= (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Student);


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isDeleted = studentBO.deleteStudent(
                    new StudentDTO(
                            lblStudentID.getText(),
                            txtName.getText(),
                            txtAddress.getText(),
                            txtContact.getText(),
                            txtDOB.getText(),
                            cmbGender.getSelectionModel().getSelectedItem()
                    )
            );

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Deleted").show();
                setFieldsActivation(true,false);
                clearFields();
                tblStudents.getItems().clear();
                loadAllStudents();
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws Exception {
        if (btnAdd.getText().equals("Save")){
            saveStudent();
        }else{

            updateStudent();
        }
    }

    private void saveStudent() throws Exception {
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isSaved = studentBO.saveStudent(
                    new StudentDTO(
                            lblStudentID.getText(),
                            txtName.getText(),
                            txtAddress.getText(),
                            txtContact.getText(),
                            txtDOB.getText(),
                            cmbGender.getSelectionModel().getSelectedItem()
                    )
            );

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Student saved").show();
                setFieldsActivation(true,false);
                clearFields();
                tblStudents.getItems().clear();
                loadAllStudents();

                btnCancel.setDisable(true);
                btnAddStudent.setDisable(false);
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    private void updateStudent() throws Exception{
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isUpdated = studentBO.updateStudent(
                    new StudentDTO(
                            lblStudentID.getText(),
                            txtName.getText(),
                            txtAddress.getText(),
                            txtContact.getText(),
                            txtDOB.getText(),
                            cmbGender.getSelectionModel().getSelectedItem()
                    )
            );

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Room updated").show();
                setFieldsActivation(true,false);
                clearFields();
                tblStudents.getItems().clear();
                loadAllStudents();
                btnCancel.setDisable(true);
                btnAddStudent.setDisable(false);
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    @FXML
    void initialize() throws Exception{
        setProperties();
        loadAllStudents();
        getData();

        //search students
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                    loadStudentsOnSearch(newValue);
                });
    }
    void loadStudentsOnSearch(String searchText) {
        ObservableList<StudentDTO> observableList=FXCollections.observableArrayList();
        try {
            List<StudentDTO> studentDTOS = studentBO.loadAll();
            for (StudentDTO studentDTO : studentDTOS) {
                if (studentDTO.getContactNo().contains(searchText)||studentDTO.getGender().contains(searchText) || studentDTO.getAddress().contains(searchText)|| studentDTO.getName().contains(searchText)) {
                    observableList.add(studentDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblStudents.setItems(observableList);
    }

    public void btnAddNewStudentOnAction(ActionEvent actionEvent) throws Exception {
        btnAdd.setText("Save");
        btnAddStudent.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        setFieldsActivation(false,false);
        setID();
        setCmbGender();
    }

    private void setProperties(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void loadAllStudents() throws Exception {
        List<StudentDTO> studentDTOS = studentBO.loadAll();
        ObservableList<StudentDTO> observableList= FXCollections.observableList(studentDTOS);
        tblStudents.setItems(observableList);
    }

    private void setID() throws Exception {
        lblStudentID.setText(studentBO.generateNextStudentID());

        //System.out.println(studentBO.generateNextStudentID());
    }

    private void setFieldsActivation(boolean isActivate,boolean isStatus){

        txtName.setDisable(isActivate);
        txtAddress.setDisable(isActivate);
        txtContact.setDisable(isActivate);
        txtDOB.setDisable(isActivate);
        cmbGender.setDisable(isActivate);
        btnClear.setDisable(isActivate);
        btnAdd.setDisable(isActivate);

        if (!isActivate){
            if (isStatus){
                btnDelete.setDisable(false);
                btnAdd.setText("Update");
            }
        }
    }

    private void setCmbGender(){
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");


        ObservableList<String> observableList = FXCollections.observableList(genders);
        cmbGender.setItems(observableList);
    }

    private boolean checkValidation(){
        String nameText = txtName.getText();
        String addressText = txtAddress.getText();
        String dobText = txtDOB.getText();//matches("\\d{4}-\\d{2}-\\d{2}")
        String contactText = txtContact.getText();

        if (!nameText.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return false;
        } else if (!addressText.matches(".{2,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtAddress.requestFocus();
            return false;
        } else if (!dobText.matches("\\d{4}-\\d{2}-\\d{2}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            txtDOB.requestFocus();
            return false;
        }else if (!contactText.matches(".*(?:7|0|(?:\\\\+94))[0-9]{9,10}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            txtContact.requestFocus();
            return false;
        }else {
            return true;
        }

    }

    private void clearFields(){
        txtName.clear();
        txtContact.clear();
        txtAddress.clear();
        txtDOB.clear();
        lblStudentID.setText(" ");
        cmbGender.getSelectionModel().clearSelection();
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        btnCancel.setDisable(true);
        btnAddStudent.setDisable(false);
        clearFields();
        setFieldsActivation(true,false);
    }

    void getData(){
        tblStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setFieldsActivation(false,true);
            btnCancel.setDisable(true);

            btnAdd.setText(newValue != null ? "Update" : "Save");
            btnAdd.setDisable(newValue == null);

            if (newValue != null) {

                if (studentBO.getCount(newValue.getId())){
                    btnDelete.setVisible(true);
                }else {
                    btnDelete.setVisible(false);
                }

                lblStudentID.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtDOB.setText(newValue.getDob());
                txtContact.setText(newValue.getContactNo());

                if (newValue.getGender().equals("Male")){
                    setCmbGender();
                    cmbGender.getSelectionModel().select(0);
                }else cmbGender.getSelectionModel().select(1);
            }
        });
    }
}

