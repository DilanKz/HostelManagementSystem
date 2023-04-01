package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class LoginFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnChngSign;

    @FXML
    private Pane signPane;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private Pane signInPane;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private JFXButton btnForgetPassword;

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
    void btnChangePassOnAction(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
