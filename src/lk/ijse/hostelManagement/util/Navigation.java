package lk.ijse.hostelManagement.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    public static AnchorPane anchorPane;
    public static Stage stage;

    public enum Routes{
        Student,Rooms,Reservation,Login
    }

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.anchorPane = pane;
        Navigation.anchorPane.getChildren().clear();
        stage = (Stage) Navigation.anchorPane.getScene().getWindow();
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");

        switch (route){
            case Login :
                initUI("");
                break;
            case Student:
                initUI("/lk/ijse/hostelManagement/view/StudentForm.fxml");
                break;
            case Rooms:
                initUI("/lk/ijse/hostelManagement/view/RoomForm.fxml");
                break;
            case Reservation:
                initUI("/lk/ijse/hostelManagement/view/ReservationForm.fxml");
                break;
        }
    }

    public static void initUI(String location) throws IOException {
        Navigation.anchorPane.getChildren().add(FXMLLoader.load(Navigation.class.getResource(location)));
    }


}
