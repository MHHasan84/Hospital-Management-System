package com.example;

public class Appointment {
    private int appointment_id;
    private String doctor_id;
    private String patient_id;
    private int schedule_id;
    private String appointment_date;
    private String visiting_date;
    private String status;
    private String prescription;

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
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

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getVisiting_date() {
        return visiting_date;
    }

    public void setVisiting_date(String visiting_date) {
        this.visiting_date = visiting_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointment_id=" + appointment_id +
                ", doctor_id='" + doctor_id + '\'' +
                ", patient_id='" + patient_id + '\'' +
                ", schedule_id=" + schedule_id +
                ", appointment_date='" + appointment_date + '\'' +
                ", visiting_date='" + visiting_date + '\'' +
                ", status='" + status + '\'' +
                ", prescription='" + prescription + '\'' +
                '}';
    }
}
