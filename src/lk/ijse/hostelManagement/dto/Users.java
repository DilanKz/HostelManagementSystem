package lk.ijse.hostelManagement.dto;


public class Users {
    private String id;
    private String userName;
    private String password;
    private String contact;
    private String type;
    private boolean isEnabled;

    public Users() {
    }

    public Users(String id, String userName, String password, String contact, String type, boolean isEnabled) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.contact = contact;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
