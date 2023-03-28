package lk.ijse.hostelManagement.dao.custom;

import lk.ijse.hostelManagement.dao.CrudDAO;
import lk.ijse.hostelManagement.entity.Room;

public interface RoomsDAO extends CrudDAO<Room> {
    void setSession() throws Exception;
}
