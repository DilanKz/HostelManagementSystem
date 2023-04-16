package lk.ijse.hostelManagement.dto;

public class LogsDTO {
    private String time;
    private String data;

    public LogsDTO() {
    }

    public LogsDTO(String time, String data) {
        this.setTime(time);
        this.setData(data);
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LogsDTO{" +
                "time='" + time + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
