package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WardDao {
    public List<Ward> getAllWard(){
        List<Ward> wardList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from ward";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Ward ward=new Ward();
                ward.setWard_no(rs.getString("ward_no"));
                ward.setWard_type(rs.getString("ward_type"));
                ward.setRoom_no(rs.getString("room_no"));
                ward.setCost_per_bd(rs.getInt("cost_per_bed"));
                wardList.add(ward);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllWard: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wardList;
    }

    public Ward getWard(String ward_no){
        Ward ward=new Ward();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from ward where ward_no='%s'",ward_no);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                ward.setWard_no(rs.getString("ward_no"));
                ward.setWard_type(rs.getString("ward_type"));
                ward.setRoom_no(rs.getString("room_no"));
                ward.setCost_per_bd(rs.getInt("cost_per_bed"));
            }
            else {
                System.out.println("No ward in this id");
            }
        } catch (Exception e) {
            System.out.println("Exception in getWard: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ward;
    }

    public void insertWard(Ward ward){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String insertQuery=String.format(
                    "insert into ward(ward_no,ward_type,room_no,cost_per_bed)" +
                            " values('%s','%s','%s','%d')",ward.getWard_no(),ward.getWard_type(),
                    ward.getRoom_no(),ward.getCost_per_bd()
            );
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addWard: " + e);
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

    public void updateWard(String ward_no,Ward ward){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "update ward set ward_type='%s',room_no='%s',cost_per_bed='%d'" +
                            " where ward_no='%s'",ward.getWard_type(),ward.getRoom_no(),
                    ward.getCost_per_bd(),ward.getWard_no()
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in updateWard: " + e);
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
