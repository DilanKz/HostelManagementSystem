package lk.ijse.hostelManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;


public class Appinitializer extends Application {
    public static Stage stage=new Stage();
        public static void main(String[] args) {launch(args);}

        @Override
        public void start(Stage primaryStage) throws Exception {
            stage=primaryStage;

            Session session=SessionFactoryConfiguration.getInstance().getSession();

            Parent window = FXMLLoader.load(this.getClass().getResource("view/LoginForm.fxml"));
            Scene scene = new Scene(window);
            stage.setScene(scene);
            stage.setTitle("Login Form");
            stage.centerOnScreen();
            //primaryStage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }
}
