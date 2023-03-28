package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.UsersBO;
import lk.ijse.hostelManagement.dao.DAOFactory;
import lk.ijse.hostelManagement.dao.custom.UserDAO;
import lk.ijse.hostelManagement.dto.UsersDTO;
import lk.ijse.hostelManagement.entity.Student;
import lk.ijse.hostelManagement.entity.Users;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UsersBOImpl implements UsersBO {
    private Session session;
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Users);
    @Override
    public List<UsersDTO> loadAll() throws Exception {
        List<Users> users = userDAO.loadAll();
        List<UsersDTO> usersDTOS=new ArrayList<>();

        for (Users users1:users) {
            usersDTOS.add(
                    new UsersDTO(
                            users1.getId(),
                            users1.getUserName(),
                            users1.getPassword(),
                            users1.getContact(),
                            users1.getType(),
                            users1.isEnabled()
                    )
            );
        }

        return usersDTOS;
    }

    @Override
    public boolean saveUsers(UsersDTO usersDTO) throws Exception {
        Transaction transaction = session.beginTransaction();
        session= SessionFactoryConfiguration.getInstance().getSession();
        try{
            userDAO.setSession(session);
            userDAO.save(
                    new Users(
                            usersDTO.getId(),
                            usersDTO.getUserName(),
                            usersDTO.getPassword(),
                            usersDTO.getContact(),
                            usersDTO.getType(),
                            usersDTO.isEnabled()
                    )
            );
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean updateUsers(UsersDTO usersDTO) throws Exception {
        Transaction transaction = session.beginTransaction();
        session= SessionFactoryConfiguration.getInstance().getSession();

        try {
            userDAO.setSession(session);
            userDAO.update(
                    new Users(
                            usersDTO.getId(),
                            usersDTO.getUserName(),
                            usersDTO.getPassword(),
                            usersDTO.getContact(),
                            usersDTO.getType(),
                            usersDTO.isEnabled()
                    )
            );
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean deleteUsers(UsersDTO usersDTO) throws Exception {
        Transaction transaction = session.beginTransaction();
        session= SessionFactoryConfiguration.getInstance().getSession();

        try {
            userDAO.setSession(session);
            userDAO.delete(
                    new Users(
                            usersDTO.getId(),
                            usersDTO.getUserName(),
                            usersDTO.getPassword(),
                            usersDTO.getContact(),
                            usersDTO.getType(),
                            usersDTO.isEnabled()
                    )
            );
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public String generateNextUserID() throws Exception {
        return userDAO.generateID();
    }
}
