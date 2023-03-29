package lk.ijse.hostelManagement.dao.custom.impl;

import lk.ijse.hostelManagement.dao.custom.ReservationDAO;
import lk.ijse.hostelManagement.entity.Reservation;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private Session session;
    @Override
    public List<Reservation> loadAll() {
        try{
            String sqlQuery="From Reservation ";
            Query query = session.createQuery(sqlQuery);
            List list = query.list();
            session.close();
            return list;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public String save(Reservation reservation)  {
        return (String) session.save(reservation);
    }

    @Override
    public void update(Reservation reservation) throws Exception {
        session.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) throws Exception {
        session.delete(reservation);
    }

    @Override
    public Reservation getObject(String id) throws Exception {
        return session.get(Reservation.class,id);
    }

    @Override
    public String generateID() throws Exception {
        String sqlQuery="FROM Reservation ORDER BY id DESC";
        Query query = session.createQuery(sqlQuery);
        query.setMaxResults(1);
        Reservation reservation = (Reservation) query.uniqueResult();


        if (reservation != null){
            String lastID=reservation.getResID();
            int newReserveID=Integer.parseInt(lastID.replace("REV-",""))+1;
            return String.format("REV-%03d",newReserveID);
        }
        return "REV-001";
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session=session;
    }
}