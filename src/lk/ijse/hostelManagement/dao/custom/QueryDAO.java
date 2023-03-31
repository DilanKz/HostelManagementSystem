package lk.ijse.hostelManagement.dao.custom;

import lk.ijse.hostelManagement.dao.SuperDAO;

public interface QueryDAO extends SuperDAO {
    void getUnpaidStudents();
}
