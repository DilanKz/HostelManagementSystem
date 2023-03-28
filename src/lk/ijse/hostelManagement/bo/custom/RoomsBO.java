package lk.ijse.hostelManagement.bo.custom;

import lk.ijse.hostelManagement.bo.SuperBO;
import lk.ijse.hostelManagement.dto.RoomDTO;

import java.util.List;

public interface RoomsBO extends SuperBO {
    List<RoomDTO> loadAll() throws Exception;
    boolean saveRoom(RoomDTO roomDTO) throws Exception;
    boolean updateRoom(RoomDTO roomDTO) throws Exception;
    boolean deleteRoom(RoomDTO roomDTO) throws Exception;
    String generateNextRoomID() throws Exception;
}
