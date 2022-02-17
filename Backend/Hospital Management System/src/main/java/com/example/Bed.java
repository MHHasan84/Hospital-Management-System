package com.example;

public class Bed {
    private String bed_no;
    private String ward_no;

    public String getBed_no() {
        return bed_no;
    }

    public void setBed_no(String bed_no) {
        this.bed_no = bed_no;
    }

    public String getWard_no() {
        return ward_no;
    }

    public void setWard_no(String ward_no) {
        this.ward_no = ward_no;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "bed_no='" + bed_no + '\'' +
                ", ward_no='" + ward_no + '\'' +
                '}';
    }
}
