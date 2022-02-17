package com.example;

import java.sql.ResultSet;

public class PatientDao {
    public Patient getPatient(String id){
        Patient patient=new Patient();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from patients where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                patient.setId(rs.getString("id"));
                patient.setName(rs.getString("name"));
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
                            "appointment_date,visiting_date,status,prescription)" +
                            " values('%s','%s','%d','%s','%s','%s','%s')", appointment.getDoctor_id(),
                    appointment.getPatient_id(), appointment.getSchedule_id(), appointment.getAppointment_date(),
                    appointment.getVisiting_date(), appointment.getStatus(),prs
            );
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addTest: " + e);
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
}
