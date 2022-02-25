package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BedDao {
    public List<Bed> getAllBed(){
        List<Bed> bedList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from bed";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Bed bed=new Bed();
                bed.setBed_no(rs.getString("bed_no"));
                bed.setWard_no(rs.getString("ward_no"));
                bedList.add(bed);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllBed: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bedList;
    }

    public Bed getBed(String bed_no){
        Bed bed=new Bed();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from bed where bed_no='%s'",bed_no);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                bed.setBed_no(rs.getString("bed_no"));
                bed.setWard_no(rs.getString("ward_no"));
            }
            else {
                System.out.println("No bed in this id");
            }
        } catch (Exception e) {
            System.out.println("Exception in getBed: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bed;
    }

    public List<Bed> getAllBedByWardNo(String wardNo){
        List<Bed> bedList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from bed where ward_no='%s'",wardNo);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Bed bed=new Bed();
                bed.setBed_no(rs.getString("bed_no"));
                bed.setWard_no(rs.getString("ward_no"));
                bedList.add(bed);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllBedByWardNo: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bedList;
    }

    public void insertBed(Bed bed){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String insertQuery=String.format(
                    "insert into bed(bed_no,ward_no)" +
                            " values('%s','%s')",bed.getBed_no(),bed.getWard_no()
            );
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addBed: " + e);
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

    public void updateBed(String bed_no,Bed bed){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "update bed set ward_no='%s' where bed_no='%s'",bed.getWard_no(),bed_no
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in updateBed: " + e);
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
