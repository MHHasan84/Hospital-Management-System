package com.example;

public class AdmittedPatient {
    private int admitted_id;
    private String doctor_id;
    private String patient_id;
    private String ward_no;
    private String bed_no;
    private String admitted_date;
    private String release_date;

    private String doctor_name;
    private String patient_name;

    public int getAdmitted_id() {
        return admitted_id;
    }

    public void setAdmitted_id(int admitted_id) {
        this.admitted_id = admitted_id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getWard_no() {
        return ward_no;
    }

    public void setWard_no(String ward_no) {
        this.ward_no = ward_no;
    }

    public String getBed_no() {
        return bed_no;
    }

    public void setBed_no(String bed_no) {
        this.bed_no = bed_no;
    }

    public String getAdmitted_date() {
        return admitted_date;
    }

    public void setAdmitted_date(String admitted_date) {
        this.admitted_date = admitted_date;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }
}
