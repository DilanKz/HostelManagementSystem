package lk.ijse.hostelManagement.dao.custom;

import lk.ijse.hostelManagement.dao.CrudDAO;
import lk.ijse.hostelManagement.entity.Room;
import org.hibernate.Session;

import java.util.List;

public interface RoomsDAO extends CrudDAO<Room> {
    void setSession(Session session) throws Exception;
    List<String> getIds() throws Exception;
}
