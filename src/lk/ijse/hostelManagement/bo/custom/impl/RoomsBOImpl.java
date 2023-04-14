package lk.ijse.hostelManagement.bo.custom.impl;

import lk.ijse.hostelManagement.bo.custom.RoomsBO;
import lk.ijse.hostelManagement.dao.DAOFactory;
import lk.ijse.hostelManagement.dao.custom.ReservationDAO;
import lk.ijse.hostelManagement.dao.custom.RoomsDAO;
import lk.ijse.hostelManagement.dto.RoomDTO;
import lk.ijse.hostelManagement.entity.Room;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomsBOImpl implements RoomsBO {
    private Session session;
    RoomsDAO roomsDAO = (RoomsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Rooms);
    ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Reservation);
    @Override
    public List<RoomDTO> loadAll() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        roomsDAO.setSession(session);
        List<Room> rooms = roomsDAO.loadAll();
        List<RoomDTO> roomDTOS=new ArrayList<>();

        for(Room room: rooms){
            roomDTOS.add(
              new RoomDTO(
                      room.getId(),
                      room.getType(),
                      room.getKeyMoney(),
                      room.getQty()
              )
            );
        }

        return roomDTOS;
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            roomsDAO.setSession(session);
            roomsDAO.save(
                    new Room(
                            roomDTO.getId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
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
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            roomsDAO.setSession(session);
            roomsDAO.update(
                    new Room(
                            roomDTO.getId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
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
    public boolean deleteRoom(RoomDTO roomDTO) throws Exception {
        session=SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            roomsDAO.setSession(session);
            roomsDAO.delete(
                    new Room(
                            roomDTO.getId(),
                            roomDTO.getType(),
                            roomDTO.getKeyMoney(),
                            roomDTO.getQty()
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
    public String generateNextRoomID() throws Exception {
        session= SessionFactoryConfiguration.getInstance().getSession();
        roomsDAO.setSession(session);
        return roomsDAO.generateID();
    }

    @Override
    public boolean getCount(String roomID){
        session=SessionFactoryConfiguration.getInstance().getSession();
        try {
            reservationDAO.setSession(session);
            return reservationDAO.getRoomCount(roomID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
