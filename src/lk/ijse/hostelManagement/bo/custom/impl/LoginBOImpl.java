package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.LoginBO;
import lk.ijse.hostelManagement.dao.DAOFactory;
import lk.ijse.hostelManagement.dao.custom.UserDAO;
import lk.ijse.hostelManagement.dto.UsersDTO;
import lk.ijse.hostelManagement.entity.Users;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LoginBOImpl implements LoginBO {
    private Session session;
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Users);

    @Override
    public boolean saveUsers(UsersDTO usersDTO) throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            userDAO.setSession(session);
            userDAO.save(
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
    public UsersDTO getUsersDto(String userName) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);

        Users users = userDAO.getUsers(userName);
        return new UsersDTO(
                users.getId(),
                users.getName(),
                users.getUserName(),
                users.getPassword(),
                users.getEmail(),
                users.getType(),
                users.isEnabled()
        );
    }

    @Override
    public List<UsersDTO> loadAll() throws Exception {
        return null;
    }
    @Override
    public String generateNextUserID() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        return userDAO.generateID();
    }
}
