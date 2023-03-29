package lk.ijse.hostelManagement.bo;

import lk.ijse.hostelManagement.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hostelManagement.bo.custom.impl.RoomsBOImpl;
import lk.ijse.hostelManagement.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostelManagement.bo.custom.impl.UsersBOImpl;
import lk.ijse.hostelManagement.dao.SuperDAO;
import lk.ijse.hostelManagement.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hostelManagement.dao.custom.impl.RoomsDAOImpl;
import lk.ijse.hostelManagement.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostelManagement.dao.custom.impl.UserDAOImpl;

public class BOFactory {
    private static BOFactory BOFactory;

    public BOFactory() {
    }

    public static BOFactory getInstance(){
        if (BOFactory ==null) {
            BOFactory =new BOFactory();
        }
        return BOFactory;
    }

    public enum BOTypes {
        Student,Rooms,Reservation,Users
    }


    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Student :
                return new StudentBOImpl();
            case Rooms:
                return new RoomsBOImpl();
            case Reservation:
                return new ReservationBOImpl();
            case Users:
                return new UsersBOImpl();
            default:
                return null;
        }
    }


}
