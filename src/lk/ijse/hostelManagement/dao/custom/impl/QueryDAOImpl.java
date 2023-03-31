package lk.ijse.hostelManagement.dao.custom.impl;

import lk.ijse.hostelManagement.dao.custom.QueryDAO;
import lk.ijse.hostelManagement.projection.StudentDetailsDTO;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    private Session session;
    @Override
    public List<StudentDetailsDTO> getUnpaidStudents() {
        session= SessionFactoryConfiguration.getInstance().getSession();

        String sql="SELECT new lk.ijse.hostelManagement.projection.StudentDetailsDTO(s.id, s.name, s.contactNo,r.date ,r.id,r.room) FROM Student s INNER JOIN s.reservationList r WHERE r.status = 'unPaid'";

        Query query = session.createQuery(sql);
        List<StudentDetailsDTO> list = query.list();
        session.close();
        return list;
    }
}
