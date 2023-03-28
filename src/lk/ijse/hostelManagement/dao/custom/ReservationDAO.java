package lk.ijse.hostelManagement.dao.custom;

import lk.ijse.hostelManagement.dao.CrudDAO;
import lk.ijse.hostelManagement.entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation> {
    void setSession() throws Exception;
}
