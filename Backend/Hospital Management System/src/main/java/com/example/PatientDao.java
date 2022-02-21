package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {
    DoctorDao doctorDao=new DoctorDao();
    public Patient getPatient(String id){
        Patient patient=new Patient();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from patients where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                patient.setId(rs.getString("id"));
            }
            else {
                System.out.println("No patient in this id");
            }
        } catch (Exception e) {
            System.out.println("Exception in getPatient: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patient;
    }

    public void insertAppointment(Appointment appointment){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String insertQuery=String.format(
                    "insert into appointments(doctor_id,patient_id,schedule_id," +
                            "appointment_date,status,prescription_id)" +
                            " values('%s','%s','%d','%s','%s')", appointment.getDoctor_id(),
                    appointment.getPatient_id(), appointment.getSchedule_id(), appointment.getAppointment_date(),
                    appointment.getStatus()
            );
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addAppointment: " + e);
        }
        finally {
            try {
                oc.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Appointment> getAllAppointments(String patientId){
        List<Appointment> appointmentList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from appointments where patient_id='%s'",patientId);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Appointment appointment=new Appointment();
                appointment.setAppointment_id(rs.getInt("appointment_id"));
                appointment.setDoctor_id(rs.getString("doctor_id"));
                appointment.setPatient_id(rs.getString("patient_id"));
                appointment.setSchedule_id(rs.getInt("schedule_id"));
                appointment.setAppointment_date(rs.getString("appointment_date"));
                appointment.setStatus(rs.getString("status"));

                Doctor doctor= doctorDao.getDoctor(appointment.getDoctor_id());
                DoctorSchedule doctorSchedule= doctorDao.getDoctorSchedule(appointment.getSchedule_id());
                appointment.setDoctor_name(doctor.getFirst_name()+" "+doctor.getLast_name());
                appointment.setVisiting_date(doctorSchedule.getSchedule_date());
                appointment.setVisiting_time(doctorSchedule.getStart_time()+" - "+doctorSchedule.getEnd_time());

                appointmentList.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllAppointment: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appointmentList;
    }
}
