package lk.ijse.hostelManagement.bo.custom;

import lk.ijse.hostelManagement.bo.SuperBO;
import lk.ijse.hostelManagement.dto.UsersDTO;

import java.util.List;

public interface LoginBO extends SuperBO {
    boolean saveUsers(UsersDTO usersDTO) throws Exception;

    boolean updateUsers(UsersDTO usersDTO) throws Exception;

    UsersDTO getUsersDto(String userName) throws Exception;

    List<UsersDTO> loadAll() throws Exception;

    String generateNextUserID() throws Exception;
}
