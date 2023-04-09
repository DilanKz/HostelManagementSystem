package lk.ijse.hostelManagement.dao.custom.impl;

import lk.ijse.hostelManagement.dao.custom.UserDAO;
import lk.ijse.hostelManagement.entity.Users;
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

            String sqlQuery="FROM Users ORDER BY id DESC";
            Query query = session.createQuery(sqlQuery);
            query.setMaxResults(1);
            Users users = (Users) query.uniqueResult();

        if (users != null){
            String lastID=users.getId();
            int newUserID=Integer.parseInt(lastID.replace("U-",""))+1;
            return String.format("U-%03d",newUserID);
        }
        return "U-001";
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }

    @Override
    public Users getUsers(String userName) {
        String hql="from Users where userName=:un";
        Query query = session.createQuery(hql);
        query.setParameter("un",userName);
        Users users = (Users) query.uniqueResult();

        return users;
    }

    @Override
    public boolean activateUser(String id){
        String hql="UPDATE Users u SET u.isEnabled=true WHERE u.id=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        int count = query.executeUpdate();
        return count >= 0;
    }
}
