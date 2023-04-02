package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;

public class LoginFormController {

    public SVGPath svgPassEye;
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

    public void btnSignUpOnAction(ActionEvent actionEvent) {
        checkValidation();
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
            //new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return false;
        } else if (!userName.matches("[A-Za-z ]+")) {
            //new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtUname.requestFocus();
            return false;
        } else if (!email.matches("^(.+)@(.+)$")) {
            //new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            txtEmail.requestFocus();
            return false;
        } else if (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
            //new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            txtPass.requestFocus();
            txtShowNewPass.requestFocus();
        }
        return true;
    }
}
