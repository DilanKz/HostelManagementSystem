package lk.ijse.hostelManagement.dao.custom.impl;

import lk.ijse.hostelManagement.dao.custom.StudentDAO;
import lk.ijse.hostelManagement.entity.Reservation;
import lk.ijse.hostelManagement.entity.Student;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Session session;

    @Override
    public List<Student> loadAll() throws Exception {
        String sqlQuery="From Student";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Student student) throws Exception {
        return (String) session.save(student);
    }

    @Override
    public void update(Student student) throws Exception {
        session.update(student);
    }

    @Override
    public void delete(Student student) throws Exception {
        session.delete(student);
    }

    @Override
    public Student getObject(String id) throws Exception {
        return session.get(Student.class,id);
    }

    @Override
    public String generateID() throws Exception {
        Student student = null;
        try {
            String sqlQuery="FROM Reservation ORDER BY id DESC";
            Query query = session.createQuery(sqlQuery);
            query.setMaxResults(1);
            student = (Student) query.uniqueResult();
        }catch (Exception e){

        }

        String lastID=student.getId();

        if (lastID != null){
            int newStudentID=Integer.parseInt(lastID.replace("STU-",""))+1;
            return String.format("STU-%03d",newStudentID);
        }
        return "STU-001";
    }

    @Override
    public void setSession() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
    }
}
