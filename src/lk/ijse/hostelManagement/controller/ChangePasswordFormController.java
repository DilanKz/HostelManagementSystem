package lk.ijse.hostelManagement.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.SVGPath;

public class ChangePasswordFormController {

    public JFXButton btnShowP;
    public JFXButton btnShowNP;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtShowPass;

    @FXML
    private TextField txtShowConfirmPass;

    @FXML
    private PasswordField txtpsPass;

    @FXML
    private PasswordField txtpConfirmPass;

    @FXML
    private JFXButton btnChangePassword;

    @FXML
    private SVGPath svgEyeNP;

    @FXML
    private SVGPath svgEyeShowNP;

    @FXML
    private Label lblCurrentStatus;

    private String eyeOpen="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z";
    private String eyeClosed="m10.79 12.912-1.614-1.615a3.5 3.5 0 0 1-4.474-4.474l-2.06-2.06C.938 6.278 0 8 0 8s3 5.5 8 5.5a7.029 7.029 0 0 0 2.79-.588zM5.21 3.088A7.028 7.028 0 0 1 8 2.5c5 0 8 5.5 8 5.5s-.939 1.721-2.641 3.238l-2.062-2.062a3.5 3.5 0 0 0-4.474-4.474L5.21 3.089z M5.525 7.646a2.5 2.5 0 0 0 2.829 2.829l-2.83-2.829zm4.95.708-2.829-2.83a2.5 2.5 0 0 1 2.829 2.829zm3.171 6-12-12 .708-.708 12 12-.708.708z";

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) {
        if (txtShowConfirmPass.getText().equals(txtShowPass.getText())){

        }
    }

    @FXML
    void initialize() {

    }

    @FXML
    void txtpsPassKeyEvent(KeyEvent keyEvent) {
        if (txtpsPass.getText() != null){
            btnShowP.setVisible(true);
        }

        if (txtpsPass.getText() == null ||txtpsPass.getText().equals("")||txtpsPass.getText().equals(" ")){
            btnShowP.setVisible(false);
        }

        if (txtpConfirmPass.getText() == null ||txtpConfirmPass.getText().equals("")||txtpConfirmPass.getText().equals(" ")
                ||txtpsPass.getText() == null ||txtpsPass.getText().equals("")||txtpsPass.getText().equals(" ")){
            btnChangePassword.setDisable(true);
        }else {
            btnChangePassword.setDisable(false);
        }
    }

    @FXML
    void txtpConfirmPassKeyEvent(KeyEvent keyEvent) {
        if (txtpConfirmPass.getText() != null){
            btnShowNP.setVisible(true);
        }

        if (txtpConfirmPass.getText() == null ||txtpConfirmPass.getText().equals("")||txtpConfirmPass.getText().equals(" ")){
            btnShowNP.setVisible(false);
        }

        if (txtpConfirmPass.getText() == null ||txtpConfirmPass.getText().equals("")||txtpConfirmPass.getText().equals(" ")
                ||txtpsPass.getText() == null ||txtpsPass.getText().equals("")||txtpsPass.getText().equals(" ")){
            btnChangePassword.setDisable(true);
        }else {
            btnChangePassword.setDisable(false);
        }
    }

    @FXML
    void btnShowPOnAction(ActionEvent actionEvent) {
        if(svgEyeNP.getContent().equals(eyeOpen)){
            svgEyeNP.setContent(eyeClosed);
            txtShowPass.setVisible(true);
            txtpsPass.setVisible(false);

            txtShowPass.setText(txtpsPass.getText());

        }else if(svgEyeNP.getContent().equals(eyeClosed)){
            svgEyeNP.setContent(eyeOpen);
            txtShowPass.setVisible(false);
            txtpsPass.setVisible(true);

            txtpsPass.setText(txtShowPass.getText());
        }
    }

    @FXML
    void btnShowNPOnAction(ActionEvent actionEvent) {
        if (svgEyeShowNP.getContent().equals(eyeClosed)){
            svgEyeShowNP.setContent(eyeOpen);
            txtpConfirmPass.setVisible(true);
            txtShowConfirmPass.setVisible(false);

            txtpConfirmPass.setText(txtShowConfirmPass.getText());

        }else if (svgEyeShowNP.getContent().equals(eyeOpen)){
            svgEyeShowNP.setContent(eyeClosed);
            txtpConfirmPass.setVisible(false);
            txtShowConfirmPass.setVisible(true);

            txtShowConfirmPass.setText(txtpConfirmPass.getText());
        }
    }
}
