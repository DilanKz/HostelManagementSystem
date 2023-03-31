package lk.ijse.hostelManagement.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private FlowPane mainPane;

    @FXML
    private Pane detailPane;

    @FXML
    private Pane loginPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void mouseEnterEvent(MouseEvent mouseEvent) {
        mainPane.setAlignment(Pos.BASELINE_RIGHT);
    }
}
