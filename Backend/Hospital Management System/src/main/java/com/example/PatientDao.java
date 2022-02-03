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
}
