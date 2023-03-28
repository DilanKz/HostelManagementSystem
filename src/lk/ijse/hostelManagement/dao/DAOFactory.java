package lk.ijse.hostelManagement.dao;

import lk.ijse.hostelManagement.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hostelManagement.dao.custom.impl.RoomsDAOImpl;
import lk.ijse.hostelManagement.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostelManagement.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getInstance(){
        if (daoFactory==null) {
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        Student,Rooms,Reservation,Users
    }


    public static SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case Student :
                return new StudentDAOImpl();
            case Rooms:
                return new RoomsDAOImpl();
            case Reservation:
                return new ReservationDAOImpl();
            case Users:
                return new UserDAOImpl();
            default:
                return null;
        }
    }


}
