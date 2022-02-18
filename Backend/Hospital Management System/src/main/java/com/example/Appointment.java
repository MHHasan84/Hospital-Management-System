package com.example;

public class Appointment {
    private int appointment_id;
    private String doctor_id;
    private String patient_id;
    private int schedule_id;
    private String appointment_date;
    private String status;
    private int prescription_id;

    private String doctor_name;
    private String visiting_date;
    private String visiting_time;

    private String patientName;


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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointment_id=" + appointment_id +
                ", doctor_id='" + doctor_id + '\'' +
                ", patient_id='" + patient_id + '\'' +
                ", schedule_id=" + schedule_id +
                ", appointment_date='" + appointment_date + '\'' +
                ", status='" + status + '\'' +
                ", prescription_id=" + prescription_id +
                '}';
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getVisiting_date() {
        return visiting_date;
    }

    public void setVisiting_date(String visiting_date) {
        this.visiting_date = visiting_date;
    }

    public String getVisiting_time() {
        return visiting_time;
    }

    public void setVisiting_time(String visiting_time) {
        this.visiting_time = visiting_time;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
