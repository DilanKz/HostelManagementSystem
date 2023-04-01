package lk.ijse.hostelManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
    @Column(length = 10,name = "userId")
    private String id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "contactNo")
    private String contact;

    @Column(name = "accountType")
    private String type;

    @Column(name = "isActivated")
    private boolean isEnabled;

    public Users() {
    }

    public Users(String id, String uName, String password, String contact, String type, boolean isEnabled) {
        this.id = id;
        this.userName = uName;
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

    public void setUserName(String uName) {
        this.userName = uName;
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

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", uName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + contact + '\'' +
                ", type='" + type + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
