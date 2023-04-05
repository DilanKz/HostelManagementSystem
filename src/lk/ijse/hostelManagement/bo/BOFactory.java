package lk.ijse.hostelManagement.bo;

import lk.ijse.hostelManagement.bo.custom.impl.*;
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
        Student,Rooms,Reservation,Users,Login
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
            case Login:
                return new LoginBOImpl();
            default:
                return null;
        }
    }


}
