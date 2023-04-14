package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.hostelManagement.Appinitializer;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.LoginBO;
import lk.ijse.hostelManagement.dto.UsersDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;

public class LoginFormController {

    @FXML
    private SVGPath svgPassEye;
    @FXML
    private Label lblCurrentStatus;
    @FXML
    private JFXButton btnClose;
    @FXML
    private Pane loadingBackgroundPane;
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
    private AnchorPane twoStepPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnChngSign;

    @FXML
    private Pane signPane;

    @FXML
    private JFXTextField txtUname;

    @FXML
    private JFXPasswordField txtPass;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtShowNewPass;

    @FXML
    private JFXButton btnShowSignInPass;

    @FXML
    private Pane signInPane;

    @FXML
    private JFXTextField txtInUName;

    @FXML
    private JFXPasswordField txtInPass;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private JFXButton btnForgetPassword;

    @FXML
    private JFXButton btnShowPass;

    @FXML
    private SVGPath svgEye;

    @FXML
    private JFXTextField txtShowPass;

    String eyeOpen="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z";
    String eyeClosed="m10.79 12.912-1.614-1.615a3.5 3.5 0 0 1-4.474-4.474l-2.06-2.06C.938 6.278 0 8 0 8s3 5.5 8 5.5a7.029 7.029 0 0 0 2.79-.588zM5.21 3.088A7.028 7.028 0 0 1 8 2.5c5 0 8 5.5 8 5.5s-.939 1.721-2.641 3.238l-2.062-2.062a3.5 3.5 0 0 0-4.474-4.474L5.21 3.089z M5.525 7.646a2.5 2.5 0 0 0 2.829 2.829l-2.83-2.829zm4.95.708-2.829-2.83a2.5 2.5 0 0 1 2.829 2.829zm3.171 6-12-12 .708-.708 12 12-.708.708z";

    public static Stage stage;
    FXMLLoader loader= new FXMLLoader((getClass().getResource("../view/TwoFactorForm.fxml")));

    public static String email;

    String randomNumber;
    String place;
    private UsersDTO usersDTO;

    private LoginBO loginBO= (LoginBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Login);
    @FXML
    void btnChangeSignOnAction(ActionEvent event) {
        if(btnChngSign.getText().equals("Sign UP")){
            btnChngSign.setText("Sign IN");

            signInPane.setVisible(false);
            signPane.setVisible(true);

        }else if(btnChngSign.getText().equals("Sign IN")){
            btnChngSign.setText("Sign UP");

            signInPane.setVisible(true);
            signPane.setVisible(false);
        }
    }

    @FXML
    void btnChangePassOnAction(ActionEvent event){
        try {
            if (txtInUName.getText()!=null){
                UsersDTO usersDto = loginBO.getUsersDto(txtInUName.getText());
                usersDTO=usersDto;
                place="PassChange";

                ChangePasswordFormController.usersDTO=usersDto;

                loadingBackgroundPane.setVisible(true);
                twoStepPane.setVisible(true);
                makeCode();
                sentEmail(randomNumber, usersDto.getEmail());
                //Mail.getInstance().sentMail(randomNumber, usersDTO.getEmail());

            }else{
                txtInUName.requestFocus();
                lblCurrentStatus.setText("* Wrong username");
            }
        }catch (Exception e){
            txtInUName.requestFocus();
            lblCurrentStatus.setText("* Wrong username");
        }
    }

    @FXML
    void initialize() {

    }
    @FXML
    void btnShowPassOnAction(ActionEvent actionEvent) {

      if(svgEye.getContent().equals(eyeOpen)){
          svgEye.setContent(eyeClosed);
          //changing password field with the text field
          txtShowPass.setVisible(true);
          txtInPass.setVisible(false);

      }else if (svgEye.getContent().equals(eyeClosed)){
          svgEye.setContent(eyeOpen);
          txtShowPass.setVisible(false);
          txtInPass.setVisible(true);
      }
      txtInPass.setText(txtShowPass.getText());
    }


    public void txtPassFieldOnKeyTyped(KeyEvent keyEvent) {
        //checking the password field to setVisible the showPass button
        if (txtInPass.getText() != null){
            btnShowPass.setVisible(true);
        }

        if (txtInPass.getText() == null ||txtInPass.getText().equals("")||txtInPass.getText().equals(" ")){
            btnShowPass.setVisible(false);
        }


        //getting password field text
        txtShowPass.setText(txtInPass.getText());
    }

    public void btnSignUpOnAction(ActionEvent actionEvent) throws Exception {
        boolean isValidate = checkValidation();
        if (isValidate){
            String id=loginBO.generateNextUserID();

            boolean accountStatus=false;
            String accountType="user";
            if (id.equals("U-001")){
                accountStatus=true;
                accountType="Admin";
            }
            place="NewUser";
            email=txtEmail.getText();
            UsersDTO usersDto = new UsersDTO(
                    id,
                    txtName.getText(),
                    txtUname.getText(),
                    txtPass.getText(),
                    txtEmail.getText(),
                    accountType,
                    accountStatus
            );

            usersDTO=usersDto;

            UserSettingsFormController.usersDTO=usersDto;

            loadingBackgroundPane.setVisible(true);
            twoStepPane.setVisible(true);
            makeCode();
            sentEmail(randomNumber, usersDto.getEmail());
            //Mail.getInstance().sentMail(randomNumber, usersDto.getEmail());

        } else {
            System.out.println("Not Working yet");
        }
    }

    public void btnShowSignInPassOnAction(ActionEvent actionEvent) {
        if(svgPassEye.getContent().equals(eyeOpen)){
            svgPassEye.setContent(eyeClosed);
            //changing password field with the text field
            txtShowNewPass.setVisible(true);
            txtPass.setVisible(false);

        }else if (svgPassEye.getContent().equals(eyeClosed)){
            svgPassEye.setContent(eyeOpen);
            //changing password field with the text field
            txtShowNewPass.setVisible(false);
            txtPass.setVisible(true);
        }

        txtPass.setText(txtShowNewPass.getText());
    }

    public void txtPassOnActon(KeyEvent actionEvent) {
        if (txtPass.getText() != null){
            btnShowSignInPass.setVisible(true);
        }

        if (txtPass.getText() == null ||txtPass.getText().equals("")||txtPass.getText().equals(" ")){
            btnShowSignInPass.setVisible(false);
        }

        txtShowNewPass.setText(txtPass.getText());
    }

    private boolean checkValidation(){

        String name=txtName.getText();
        String userName=txtUname.getText();
        String email = txtEmail.getText();
        String password = txtShowNewPass.getText();

        //"^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"
        if (!name.matches("[A-Za-z ]+")) {
            txtName.requestFocus();
            return false;
        } else if (!userName.matches("[A-Za-z ]+")) {
            txtUname.requestFocus();
            return false;
        } else if (!email.matches("^(.+)@(.+)$")) {
            txtEmail.requestFocus();
            return false;
        } else if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
            txtPass.requestFocus();
            txtShowNewPass.requestFocus();
        }
        return true;
    }


    private void clearFields(){
        txtName.clear();
        txtPass.clear();
        txtShowNewPass.clear();
        txtEmail.clear();
        txtUname.clear();
    }

    @FXML
    void btnSignInOnAction(ActionEvent actionEvent)  {
        try {
            UsersDTO usersDto = loginBO.getUsersDto(txtInUName.getText());
            if (usersDto!=null){
                lblCurrentStatus.setText("");
                System.out.println(usersDto);
                if (usersDto.getPassword().equals(txtInPass.getText())||usersDto.getPassword().equals(txtShowPass.getText())){
                    lblCurrentStatus.setText("");
                    if (usersDto.isEnabled()==true){

                        Appinitializer.stage.close();


                        UserSettingsFormController.usersDTO=usersDto;

                        stage=new Stage();
                        Parent window = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
                        Scene scene = new Scene(window);
                        stage.setScene(scene);
                        stage.setTitle("Login Form");
                        stage.setFullScreen(true);
                        stage.setFullScreenExitHint("");
                        stage.show();

                    }else {
                        lblCurrentStatus.setText("* your account is not activated at the moment");
                    }
                }else {
                    //wrong password
                    lblCurrentStatus.setText("* wrong password");
                }
            }
        }catch (Exception e){
            lblCurrentStatus.setText("* wrong userName try again !");
            txtInUName.requestFocus();
            //e.printStackTrace();
        }
    }
//Methods for Two-step verification
    @FXML
    void btnCloseOnAction(ActionEvent actionEvent) {
        loadingBackgroundPane.setVisible(false);
        twoStepPane.setVisible(false);
    }


    @FXML
    void btnConfirmOnAction(ActionEvent event) throws Exception {
        String finalNumber = txt1.getText() + txt2.getText()+ txt3.getText()+ txt4.getText()+ txt5.getText()+ txt6.getText();
        if (place.equals("NewUser")){
            if (finalNumber.equals(randomNumber)){

                loginBO.saveUsers(usersDTO);
                loadingBackgroundPane.setVisible(false);
                twoStepPane.setVisible(false);
                clearFields();
            }
        }else if (place.equals("PassChange")){
            if (finalNumber.equals(randomNumber)){
                txtInUName.clear();
                ChangePasswordFormController.usersDTO=usersDTO;
                setWindow();

                loadingBackgroundPane.setVisible(false);
                twoStepPane.setVisible(false);

                //LoginFormController.stage.close();
            }
        }
    }

    @FXML
    void lblNewCodeClicked(MouseEvent event) {

        makeCode();
        sentEmail(randomNumber,email);
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

    private void makeCode(){
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number= rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        randomNumber = String.format("%06d", number);
    }

    private void sentEmail(String code,String email){
        final String username = "narada5382@gmail.com";
        final String password = "jozc eaoi taxc vqwm";

        /*final String username = "falonh45@gmail.com";
        final String password = "tarj qxjg drtb kcmw";*/

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
            e.printStackTrace();
        }

    }
    private void setWindow() throws IOException {
        stage=new Stage();
        Parent window = FXMLLoader.load(this.getClass().getResource("../view/ChangePasswordForm.fxml"));
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();
    }
}
