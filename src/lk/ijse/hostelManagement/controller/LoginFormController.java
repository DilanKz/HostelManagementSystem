package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.hostelManagement.Appinitializer;
import lk.ijse.hostelManagement.bo.BOFactory;
import lk.ijse.hostelManagement.bo.custom.LoginBO;
import lk.ijse.hostelManagement.dto.UsersDTO;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;

public class LoginFormController {

    public SVGPath svgPassEye;
    public Label lblCurrentStatus;
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
    void btnChangePassOnAction(ActionEvent event) throws IOException {
        String userName = txtInUName.getText();
        if (userName.length()>12){

        }else{
            txtInUName.requestFocus();
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
            email=txtEmail.getText();
            UsersDTO usersDTO = new UsersDTO(
                    id,
                    txtName.getText(),
                    txtUname.getText(),
                    txtPass.getText(),
                    txtEmail.getText(),
                    accountType,
                    accountStatus
            );

            makeWindow();

            TwoFactorFormController twoFactorFormController=loader.getController();
            //System.out.println(twoFactorFormController);
            twoFactorFormController.setUser(usersDTO);
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

    public void makeWindow() throws IOException {

        stage=new Stage();
        Parent root= loader.load();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.initOwner(Appinitializer.stage);
        stage.show();
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
                if (usersDto.getPassword().equals(txtInPass.getText())){

                    Appinitializer.stage.close();

                    stage=new Stage();
                    Parent window = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
                    Scene scene = new Scene(window);
                    stage.setScene(scene);
                    stage.setTitle("Login Form");
                    stage.setFullScreen(true);
                    stage.setFullScreenExitHint("");
                    stage.show();
                    stage.centerOnScreen();
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
}
