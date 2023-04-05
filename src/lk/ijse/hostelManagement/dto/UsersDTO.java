package lk.ijse.hostelManagement.dto;


public class UsersDTO {
    private String id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private String type;
    private boolean isEnabled;

    public UsersDTO() {
    }

    public UsersDTO(String id, String name, String userName, String password, String email, String type, boolean isEnabled) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.type = type;
        this.isEnabled = isEnabled;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "UsersDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
