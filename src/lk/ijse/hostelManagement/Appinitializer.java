package lk.ijse.hostelManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class Appinitializer extends Application {
        public static void main(String[] args) {launch(args);}

        @Override
        public void start(Stage primaryStage) throws Exception {

            Session session = SessionFactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            Parent window = FXMLLoader.load(this.getClass().getResource("view/MainForm.fxml"));
            Scene scene = new Scene(window);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login Form");
            primaryStage.setFullScreenExitHint("");
            primaryStage.setFullScreen(true);
            primaryStage.show();
        }
}
