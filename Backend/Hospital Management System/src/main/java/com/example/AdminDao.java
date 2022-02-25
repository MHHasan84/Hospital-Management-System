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
                department.setId(rs.getInt("id"));
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

}
