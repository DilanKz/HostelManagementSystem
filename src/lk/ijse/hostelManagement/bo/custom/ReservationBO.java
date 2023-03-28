package lk.ijse.hostelManagement.bo.custom;

import lk.ijse.hostelManagement.bo.SuperBO;
import lk.ijse.hostelManagement.dto.ReservationDTO;
import lk.ijse.hostelManagement.dto.RoomDTO;
import lk.ijse.hostelManagement.dto.StudentDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    List<ReservationDTO> loadAll() throws Exception;
    boolean saveReservation(ReservationDTO reservationDTO) throws Exception;
    boolean updateReservation(ReservationDTO reservationDTO) throws Exception;
    StudentDTO getStudent(String id) throws Exception;
    RoomDTO getRoom(String id) throws Exception;

    boolean deleteReservation(ReservationDTO reservationDTO) throws Exception;

    String generateNextReservationID() throws Exception;
}
