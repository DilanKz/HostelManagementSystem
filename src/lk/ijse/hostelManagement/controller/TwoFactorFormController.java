package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

public class TwoFactorFormController {

    public JFXButton btnConfirm;
    public Stage stage;
    @FXML
    private Label lblMain;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    private String randomNumber;

    public static String email;

    public static String place;

    private UsersDTO usersDTO;

    LoginBO loginBO= (LoginBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Login);
    @FXML
    void btnConfirmOnAction(ActionEvent event) throws Exception {
        String finalNumber = txt1.getText() + txt2.getText()+ txt3.getText()+ txt4.getText()+ txt5.getText()+ txt6.getText();
        if (lblMain.getText().equals("Verify your email ")){
            if (finalNumber.equals(randomNumber)){

                System.out.println(randomNumber);
                System.out.println(finalNumber);
                System.out.println(usersDTO);

                LoginFormController.stage.close();
                loginBO.saveUsers(usersDTO);
            }
        }
    }

    @FXML
    void lblNewCodeClicked(MouseEvent event) {
        email =LoginFormController.email;

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

    @FXML
    void initialize() {
        email =LoginFormController.email;
        makeCode();
        sentEmail(randomNumber,email);
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
        final String username = "falonh45@gmail.com";
        final String password = "huxs xfzr muoh urzm";

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

    public void setUser(UsersDTO usersDTO) {
        this.usersDTO=usersDTO;
        System.out.println(usersDTO);
    }

}
