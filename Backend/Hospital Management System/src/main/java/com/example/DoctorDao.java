package com.example;

import java.sql.ResultSet;

public class DoctorDao {
    public Doctor getDoctor(String id){
        Doctor doctor=new Doctor();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from doctors where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                doctor.setId(rs.getString("id"));
                doctor.setName(rs.getString("name"));
            }
            else {
                System.out.println("No doctor in this id");
            }
        } catch (Exception e) {
            System.out.println("Exception in getDoctor: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doctor;
    }
}
