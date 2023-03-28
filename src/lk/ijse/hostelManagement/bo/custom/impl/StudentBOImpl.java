package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.StudentBO;
import lk.ijse.hostelManagement.dao.DAOFactory;
import lk.ijse.hostelManagement.dao.custom.StudentDAO;
import lk.ijse.hostelManagement.dto.StudentDTO;
import lk.ijse.hostelManagement.entity.Student;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    private Session session;
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Student);
    @Override
    public List<StudentDTO> loadAll() throws Exception {
        List<Student> students = studentDAO.loadAll();
        List<StudentDTO>studentDTOS=new ArrayList<>();

        for (Student student:students) {
            studentDTOS.add(
                    new StudentDTO(
                            student.getId(),
                            student.getName(),
                            student.getAddress(),
                            student.getContactNo(),
                            student.getDob(),
                            student.getGender()
                    )
            );
        }

        return studentDTOS;
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        Transaction transaction = session.beginTransaction();
        session= SessionFactoryConfiguration.getInstance().getSession();
        try{
            studentDAO.setSession(session);
            studentDAO.save(
                    new Student(
                        studentDTO.getId(),
                        studentDTO.getName(),
                        studentDTO.getAddress(),
                        studentDTO.getContactNo(),
                        studentDTO.getDob(),
                        studentDTO.getGender()
            ));
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        Transaction transaction = session.beginTransaction();
        session=SessionFactoryConfiguration.getInstance().getSession();

        try {
            studentDAO.setSession(session);
            studentDAO.update(
                    new Student(
                            studentDTO.getId(),
                            studentDTO.getName(),
                            studentDTO.getAddress(),
                            studentDTO.getContactNo(),
                            studentDTO.getDob(),
                            studentDTO.getGender()
                    ));
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) throws Exception {
        Transaction transaction = session.beginTransaction();
        session=SessionFactoryConfiguration.getInstance().getSession();

        try {
            studentDAO.setSession(session);
            studentDAO.delete(
                    new Student(
                            studentDTO.getId(),
                            studentDTO.getName(),
                            studentDTO.getAddress(),
                            studentDTO.getContactNo(),
                            studentDTO.getDob(),
                            studentDTO.getGender()
                    ));
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public String generateNextStudentID() throws Exception {
        return studentDAO.generateID();
    }
}
