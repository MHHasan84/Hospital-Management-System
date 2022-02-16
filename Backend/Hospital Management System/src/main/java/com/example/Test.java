package com.example;

public class Test {
    private String id;
    private String test_name;
    private String room_no;
    private String technitian_id;
    private int cost;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getTechnitian_id() {
        return technitian_id;
    }

    public void setTechnitian_id(String technitian_id) {
        this.technitian_id = technitian_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", test_name='" + test_name + '\'' +
                ", room_no='" + room_no + '\'' +
                ", technitian_id='" + technitian_id + '\'' +
                ", cost=" + cost +
                ", status='" + status + '\'' +
                '}';
    }
}
