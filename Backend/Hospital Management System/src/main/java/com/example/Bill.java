package com.example;

public class Bill {
    private int bill_id;
    private String patient_id;
    private String bill_type;
    private String bill_create_date;
    private String bill_clear_date;
    private String bill_status;

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getBill_create_date() {
        return bill_create_date;
    }

    public void setBill_create_date(String bill_create_date) {
        this.bill_create_date = bill_create_date;
    }

    public String getBill_clear_date() {
        return bill_clear_date;
    }

    public void setBill_clear_date(String bill_clear_date) {
        this.bill_clear_date = bill_clear_date;
    }

    public String getBill_status() {
        return bill_status;
    }

    public void setBill_status(String bill_status) {
        this.bill_status = bill_status;
    }
}
