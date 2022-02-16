package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    public List<Department> getAllDepartment(){
        List<Department> departmentList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from department";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Department department=new Department();
                department.setId(rs.getString("id"));
                department.setDepartment_name(rs.getString("department_name"));
                departmentList.add(department);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllDepartment: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return departmentList;
    }

    public List<Room> getRoomListForChamber(){
        List<Room> roomList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from room";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Room room=new Room();
                room.setRoom_no(rs.getString("room_no"));
                room.setRoom_type(rs.getString("room_type"));
                roomList.add(room);
            }
        } catch (Exception e) {
            System.out.println("Exception in getRoomListForChamber: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return roomList;
    }

}
