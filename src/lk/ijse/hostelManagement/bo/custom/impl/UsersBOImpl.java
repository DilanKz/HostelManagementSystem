package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.UsersBO;
import lk.ijse.hostelManagement.dao.DAOFactory;
import lk.ijse.hostelManagement.dao.custom.UserDAO;
import lk.ijse.hostelManagement.dto.UsersDTO;
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
                            users1.getName(),
                            users1.getUserName(),
                            users1.getPassword(),
                            users1.getEmail(),
                            users1.getType(),
                            users1.isEnabled()
                    )
            );
        }

        return usersDTOS;
    }

    @Override
    public boolean saveUsers(UsersDTO usersDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateUsers(UsersDTO usersDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userDAO.setSession(session);
            userDAO.update(
                    new Users(
                            usersDTO.getId(),
                            usersDTO.getName(),
                            usersDTO.getUserName(),
                            usersDTO.getPassword(),
                            usersDTO.getEmail(),
                            usersDTO.getType(),
                            usersDTO.isEnabled()
                    )
            );
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
        }

        return false;
    }

    @Override
    public boolean deleteUsers(UsersDTO usersDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userDAO.setSession(session);
            userDAO.delete(
                    new Users(
                            usersDTO.getId(),
                            usersDTO.getName(),
                            usersDTO.getUserName(),
                            usersDTO.getPassword(),
                            usersDTO.getEmail(),
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
