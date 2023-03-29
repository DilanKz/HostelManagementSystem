package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.ReservationBO;
import lk.ijse.hostelManagement.dao.DAOFactory;
import lk.ijse.hostelManagement.dao.custom.ReservationDAO;
import lk.ijse.hostelManagement.dao.custom.RoomsDAO;
import lk.ijse.hostelManagement.dao.custom.StudentDAO;
import lk.ijse.hostelManagement.dto.ReservationDTO;
import lk.ijse.hostelManagement.dto.RoomDTO;
import lk.ijse.hostelManagement.dto.StudentDTO;
import lk.ijse.hostelManagement.entity.Reservation;
import lk.ijse.hostelManagement.entity.Room;
import lk.ijse.hostelManagement.entity.Student;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    private Session session;

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Student);
    RoomsDAO roomsDAO = (RoomsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Rooms);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Reservation);
    @Override
    public List<ReservationDTO> loadAll() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        reservationDAO.setSession(session);

        List<Reservation> reservations = reservationDAO.loadAll();
        List<ReservationDTO> reservationDTOS=new ArrayList<>();

        for (Reservation reservation:reservations) {
            reservationDTOS.add(
                    new ReservationDTO(
                        reservation.getResID(),
                        reservation.getDate(),
                        reservation.getStudent(),
                        reservation.getRoom(),
                        reservation.getStatus()
                    )
            );
        }

        return reservationDTOS;
    }

    @Override
    public boolean saveReservation(ReservationDTO reservationDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            reservationDAO.setSession(session);
            reservationDAO.save(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            reservationDTO.getStudent(),
                            reservationDTO.getRoom(),
                            reservationDTO.getStatus()
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
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            reservationDAO.setSession(session);
            reservationDAO.update(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            reservationDTO.getStudent(),
                            reservationDTO.getRoom(),
                            reservationDTO.getStatus()
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
    public StudentDTO getStudent(String id) throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();

        try {
            studentDAO.setSession(session);
            Student student = studentDAO.getObject(id);
            session.close();
            return new StudentDTO(
                    student.getId(),
                    student.getName(),
                    student.getAddress(),
                    student.getContactNo(),
                    student.getDob(),
                    student.getGender()
            );
        } catch (Exception ex){

        }
        return null;
    }

    @Override
    public RoomDTO getRoom(String id) throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();

        try {
            roomsDAO.setSession(session);
            Room room = roomsDAO.getObject(id);
            session.close();
            return new RoomDTO(
                    room.getId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            );

        } catch (Exception ex){

        }
        return null;
    }

    @Override
    public boolean deleteReservation(ReservationDTO reservationDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            reservationDAO.setSession(session);
            reservationDAO.delete(
                    new Reservation(
                            reservationDTO.getResID(),
                            reservationDTO.getDate(),
                            reservationDTO.getStudent(),
                            reservationDTO.getRoom(),
                            reservationDTO.getStatus()
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
    public String generateNextReservationID() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        reservationDAO.setSession(session);
        return reservationDAO.generateID();
    }
}
