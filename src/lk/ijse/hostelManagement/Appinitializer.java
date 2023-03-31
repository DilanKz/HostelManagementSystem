package lk.ijse.hostelManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

            Session session = SessionFactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            QueryDAOImpl queryDAO=new QueryDAOImpl();
            List<StudentDetailsDTO> unpaidStudents = queryDAO.getUnpaidStudents();
            for (StudentDetailsDTO studentDetailsDTO: unpaidStudents){
                System.out.println(studentDetailsDTO.getStudentID());
                System.out.println(studentDetailsDTO.getResID());
                System.out.println(studentDetailsDTO.getRoom().getId());
            }


            /*Parent window = FXMLLoader.load(this.getClass().getResource("view/MainForm.fxml"));
            Scene scene = new Scene(window);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login Form");
            primaryStage.setFullScreenExitHint("");
            primaryStage.setFullScreen(true);
            primaryStage.show();*/
        }
}
