package com.example;

public class Ward {
    private String ward_no;
    private String ward_type;
    private String room_no;
    private int cost_per_bd;

    public String getWard_no() {
        return ward_no;
    }

    public void setWard_no(String ward_no) {
        this.ward_no = ward_no;
    }

    public String getWard_type() {
        return ward_type;
    }

    public void setWard_type(String ward_type) {
        this.ward_type = ward_type;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public int getCost_per_bd() {
        return cost_per_bd;
    }

    public void setCost_per_bd(int cost_per_bd) {
        this.cost_per_bd = cost_per_bd;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "ward_no='" + ward_no + '\'' +
                ", ward_type='" + ward_type + '\'' +
                ", room_no='" + room_no + '\'' +
                ", cost_per_bd=" + cost_per_bd +
                '}';
    }
}
