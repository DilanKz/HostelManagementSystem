package lk.ijse.hostelManagement.dao.custom;

import lk.ijse.hostelManagement.dao.SuperDAO;
import lk.ijse.hostelManagement.entity.Student;
import lk.ijse.hostelManagement.projection.StudentDetailsDTO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<StudentDetailsDTO> getUnpaidStudents();
}
