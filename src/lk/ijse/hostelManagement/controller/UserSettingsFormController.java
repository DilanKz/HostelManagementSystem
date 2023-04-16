package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.UsersBO;
import lk.ijse.hostelManagement.dto.LogsDTO;
import lk.ijse.hostelManagement.dto.UsersDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UserSettingsFormController {

    public SVGPath svgWeak;
    public SVGPath svgGood;
    public SVGPath svgStrong;
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPasswordName;

    @FXML
    private Pane paneAdmin;
    @FXML
    private TableView<UsersDTO> tblAllUsers;
    @FXML
    private TableColumn<UsersDTO,String> colName;
    @FXML
    private TableColumn<UsersDTO,String> colUName;
    @FXML
    private TableColumn<UsersDTO,String> ColEmail;
    @FXML
    private JFXButton btnActivate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private Pane paneUser;
    @FXML
    private TableView<LogsDTO> tblHistory;
    @FXML
    private TableColumn<Object, String> colTime;
    @FXML
    private TableColumn<Object, String> ColDetails;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private AnchorPane TwoStepPane;

    @FXML
    private Pane loadingPane;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private Label lblMain;

    @FXML
    private JFXTextField txt1;

    @FXML
    private JFXTextField txt2;

    @FXML
    private JFXTextField txt3;

    @FXML
    private JFXTextField txt4;

    @FXML
    private JFXTextField txt5;

    @FXML
    private JFXTextField txt6;

    @FXML
    private JFXButton btnClose;

    String randomNumber;

    public static UsersDTO usersDTO;

    private UsersDTO changingUsersDTO;
    UsersBO usersBO= (UsersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Users);

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        loadingPane.setVisible(false);
        TwoStepPane.setVisible(false);
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) throws Exception {
        String finalNumber = txt1.getText() + txt2.getText()+ txt3.getText()+ txt4.getText()+ txt5.getText()+ txt6.getText();
        if (finalNumber.equals(randomNumber)){
            loadingPane.setVisible(false);
            TwoStepPane.setVisible(false);

            //setting fields to editable
            setActivation(false);
            btnEdit.setDisable(true);
            btnUpdate.setDisable(false);
            loadData();
            //passwordValidate(usersDTO.getPassword());
        }
    }
    void loadData(){
        txtUserName.setText(usersDTO.getUserName());
        txtName.setText(usersDTO.getName());
        txtPasswordName.setText(usersDTO.getPassword());
        txtEmail.setText(usersDTO.getEmail());
    }

    private void setActivation(boolean isDisable) {
        txtName.setDisable(isDisable);
        txtEmail.setDisable(isDisable);
        txtPasswordName.setDisable(isDisable);
        txtUserName.setDisable(isDisable);
    }

    @FXML
    void lblNewCodeClicked(MouseEvent event) {
        sentEmail(makeCode(), usersDTO.getEmail());
    }

    @FXML
    void txt1Key(KeyEvent event) {
        txt2.requestFocus();
    }

    @FXML
    void txt2Key(KeyEvent event) {
        txt3.requestFocus();
    }

    @FXML
    void txt3Key(KeyEvent event) {
        txt4.requestFocus();
    }

    @FXML
    void txt4Key(KeyEvent event) {
        txt5.requestFocus();
    }

    @FXML
    void txt5Key(KeyEvent event) {
        txt6.requestFocus();
    }

    @FXML
    void txt6Key(KeyEvent event) {
        btnConfirm.fire();
    }

    @FXML
    void initialize() throws Exception{
        txtPasswordName.textProperty().addListener((observable, oldValue, newValue) -> {
            passwordValidate(newValue);
        });
        //passwordValidate(txtPasswordName.getText());
        setProperties();
        if (usersDTO.getType().equals("Admin")){

            paneUser.setVisible(false);
            paneAdmin.setVisible(true);
            loadAll();

        }else if (usersDTO.getType().equals("user")){

            paneUser.setVisible(true);
            paneAdmin.setVisible(false);

        }
        //sentEmail(makeCode(),usersDTO.getEmail());
        getData();
        loadLogs();
    }
    private void getData(){
        tblAllUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            btnDelete.setDisable(newValue == null);
            btnActivate.setDisable(newValue == null);

            if (newValue != null) {
                changingUsersDTO=newValue;
                if (newValue.isEnabled()){
                    btnActivate.setDisable(true);
                }
                if (newValue.getId().equals("U-001")){
                    btnDelete.setDisable(true);
                }
            }
        });
    }

    public void setProperties(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        ColDetails.setCellValueFactory(new PropertyValueFactory<>("data"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    private void loadAll() throws Exception {
        List<UsersDTO> usersDTOS = usersBO.loadAll();
        ObservableList<UsersDTO> list= FXCollections.observableArrayList(usersDTOS);
        tblAllUsers.setItems(list);
    }

    public static List<LogsDTO> logsDTOList=new ArrayList<>();
    private void loadLogs(){
        System.out.println(logsDTOList);
        tblHistory.getItems().clear();
        ObservableList<LogsDTO> list= FXCollections.observableArrayList(logsDTOList);
        tblHistory.setItems(list);

        /*tblHistory.getItems().add(0, String.valueOf(LocalTime.now()));
        tblHistory.getItems().add(1,detail);*/

    }

    @FXML
    void txtChnagePassOnKey(KeyEvent keyEvent) {
        //passwordValidate(txtPasswordName.getText());
    }

    private void passwordValidate(String password){
        /*String pass=password;
        //txtPasswordName.getText();
        if (pass !=null){
            if (pass.matches("[A-Za-z ]+")){
                svgWeak.setFill(Color.RED);
                if (pass.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
                    svgWeak.setFill(Color.YELLOW);
                    svgGood.setFill(Color.YELLOW);
                    if (pass.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
                        svgWeak.setFill(Color.GREEN);
                        svgGood.setFill(Color.GREEN);
                        svgStrong.setFill(Color.GREEN);

                    }

                }
            }
        }else if (pass==null || pass.equals(" ")||pass.equals("")){
            svgWeak.setFill(Color.WHITE);
            svgGood.setFill(Color.WHITE);
            svgStrong.setFill(Color.WHITE);
        }*/
    }

    private void sentEmail(String code,String email){
        final String username = "falonh45@gmail.com";
        final String password = "tarj qxjg drtb kcmw";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("falonh45@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Verification code");
            message.setText("This is your verification code "+code);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String makeCode(){
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number= rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        randomNumber=String.format("%06d", number);
        return randomNumber;
    }

    @FXML
    void btnEditOnAction(ActionEvent actionEvent) {
        loadingPane.setVisible(true);
        TwoStepPane.setVisible(true);
        sentEmail(makeCode(),usersDTO.getEmail());
    }

    @FXML
    void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        usersDTO.setName(txtName.getText());
        usersDTO.setUserName(txtUserName.getText());
        usersDTO.setEmail(txtEmail.getText());
        usersDTO.setPassword(txtPasswordName.getText());

        boolean isUpdated = usersBO.updateUsers(usersDTO);
        if (isUpdated){
            setActivation(true);
            btnEdit.setDisable(false);
            btnUpdate.setDisable(true);
        }
    }

    @FXML
    void btnActivateOnAction(ActionEvent actionEvent) throws Exception {
        changingUsersDTO.setEnabled(true);
        boolean isActivated = usersBO.updateUsers(changingUsersDTO);
        if (isActivated){
            btnActivate.setDisable(true);
            btnDelete.setDisable(true);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        boolean isDeleted = usersBO.deleteUsers(changingUsersDTO);
        if (isDeleted){
            btnActivate.setDisable(true);
            btnDelete.setDisable(true);

            tblAllUsers.getItems().clear();
            loadAll();
        }
    }


}
