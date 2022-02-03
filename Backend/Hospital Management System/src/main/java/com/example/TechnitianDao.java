package com.example;

import java.sql.ResultSet;

public class TechnitianDao {
    public Technitian getTechnitian(String id){
        Technitian technitian=new Technitian();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from technitians where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                technitian.setId(rs.getString("id"));
                technitian.setName(rs.getString("name"));
            }
            else {
                System.out.println("No technitian in this id");
            }
        } catch (Exception e) {
            System.out.println("Exception in getTechnitian: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return technitian;
    }
}
