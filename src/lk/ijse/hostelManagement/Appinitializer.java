package lk.ijse.hostelManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.hostelManagement.dao.custom.impl.QueryDAOImpl;
import lk.ijse.hostelManagement.projection.StudentDetailsDTO;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class Appinitializer extends Application {
        public static void main(String[] args) {launch(args);}

        @Override
        public void start(Stage primaryStage) throws Exception {

            Parent window = FXMLLoader.load(this.getClass().getResource("view/LoginForm.fxml"));
            Scene scene = new Scene(window);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login Form");
            primaryStage.centerOnScreen();
            //primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.show();

        }
}
