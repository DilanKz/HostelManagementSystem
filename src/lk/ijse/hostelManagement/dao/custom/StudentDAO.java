package lk.ijse.hostelManagement.dao.custom;

import lk.ijse.hostelManagement.dao.CrudDAO;
import lk.ijse.hostelManagement.entity.Student;
import org.hibernate.Session;

public interface StudentDAO extends CrudDAO<Student> {
    void setSession(Session session) throws Exception;
}
