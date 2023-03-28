package lk.ijse.hostelManagement.dao.custom;

import lk.ijse.hostelManagement.dao.CrudDAO;
import lk.ijse.hostelManagement.entity.Users;

public interface UserDAO extends CrudDAO<Users> {
    void setSession() throws Exception;
}
