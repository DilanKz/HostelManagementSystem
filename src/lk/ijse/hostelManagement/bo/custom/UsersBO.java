package lk.ijse.hostelManagement.bo.custom;

import lk.ijse.hostelManagement.bo.SuperBO;
import lk.ijse.hostelManagement.dto.UsersDTO;

import java.util.List;

public interface UsersBO extends SuperBO {
    List<UsersDTO> loadAll() throws Exception;
    boolean saveUsers(UsersDTO usersDTO) throws Exception;
    boolean updateUsers(UsersDTO usersDTO) throws Exception;
    boolean deleteUsers(UsersDTO usersDTO) throws Exception;

    boolean activateUser(String id) throws Exception;
    String generateNextUserID() throws Exception;
}
