package lk.ijse.hostelManagement.dao.custom.impl;

import lk.ijse.hostelManagement.dao.custom.UserDAO;
import lk.ijse.hostelManagement.entity.Room;
import lk.ijse.hostelManagement.entity.Users;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private Session session;
    @Override
    public List<Users> loadAll() throws Exception {
        String sqlQuery="From Users ";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public String save(Users users) throws Exception {
        return (String) session.save(users);
    }

    @Override
    public void update(Users users) throws Exception {
        session.update(users);
    }

    @Override
    public void delete(Users users) throws Exception {
        session.delete(users);
    }

    @Override
    public Users getObject(String id) throws Exception {
        return session.get(Users.class,id);
    }

    @Override
    public String generateID() throws Exception {
        Users users = null;
        try {
            String sqlQuery="FROM Reservation ORDER BY id DESC";
            Query query = session.createQuery(sqlQuery);
            query.setMaxResults(1);
            users = (Users) query.uniqueResult();
        }catch (Exception e){

        }

        String lastID=users.getId();

        if (lastID != null){
            int newUserID=Integer.parseInt(lastID.replace("U-",""))+1;
            return String.format("U-%03d",newUserID);
        }
        return "U-001";
    }

    @Override
    public void setSession() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
    }
}
