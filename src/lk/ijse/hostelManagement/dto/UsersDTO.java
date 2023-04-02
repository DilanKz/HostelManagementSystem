package lk.ijse.hostelManagement.dto;


public class UsersDTO {
    private String id;
    private String userName;
    private String password;
    private String email;
    private String type;
    private boolean isEnabled;

    public UsersDTO() {
    }

    public UsersDTO(String id, String userName, String password, String contact, String type, boolean isEnabled) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = contact;
        this.type = type;
        this.isEnabled = isEnabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
