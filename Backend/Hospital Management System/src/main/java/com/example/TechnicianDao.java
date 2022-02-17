package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TechnicianDao {
    public void insertTechnician(Technician technician){
        UserDao2 userDao2=new UserDao2();
        boolean user_insert_succeed=userDao2.addUser(technician.getId(), technician.getPassword(), "3");
        if(!user_insert_succeed){
            return;
        }
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();

            String insertQuery = String.format(
                    "insert into technicians(id,first_name,last_name,address,gender,phone_no,designation," +
                            "qualification,email,date_of_birth,salary) values ('%s', '%s', '%s'," +
                            " '%s', '%s', '%s', '%s', '%s', '%s','%s',%d)"
                    , technician.getId(),technician.getFirst_name(),technician.getLast_name(),technician.getAddress(),
                    technician.getGender(),technician.getPhone_no(),technician.getDesignation(),technician.getQualification(),
                    technician.getEmail(), technician.getDate_of_birth(),technician.getSalary());
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addTechnician: " + e);
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
    public Technician getTechnician(String id){
        Technician technician=new Technician();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from technicians where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                technician.setId(rs.getString("id"));
                technician.setFirst_name(rs.getString("first_name"));
                technician.setLast_name(rs.getString("last_name"));
                technician.setDate_of_birth(rs.getString("date_of_birth"));
                technician.setAddress(rs.getString("address"));
                technician.setPhone_no(rs.getString("phone_no"));
                technician.setEmail(rs.getString("email"));
                technician.setGender(rs.getString("gender"));
                technician.setDesignation(rs.getString("designation"));
                technician.setQualification(rs.getString("qualification"));
                technician.setSalary(rs.getInt("salary"));
            }
            else {
                System.out.println("No technician in this id");
            }
        } catch (Exception e) {
            System.out.println("Exception in getTechnician: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return technician;
    }

    public List<Technician> getAllTechnician(){
        List<Technician> technicianList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from technicians";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Technician technician=new Technician();
                technician.setId(rs.getString("id"));
                technician.setFirst_name(rs.getString("first_name"));
                technician.setLast_name(rs.getString("last_name"));
                technician.setDate_of_birth(rs.getString("date_of_birth"));
                technician.setAddress(rs.getString("address"));
                technician.setPhone_no(rs.getString("phone_no"));
                technician.setEmail(rs.getString("email"));
                technician.setGender(rs.getString("gender"));
                technician.setDesignation(rs.getString("designation"));
                technician.setQualification(rs.getString("qualification"));
                technician.setSalary(rs.getInt("salary"));
                technicianList.add(technician);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllTechnician: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return technicianList;
    }

    public List<Technician> getAllTechnicianById(String id){
        List<Technician> technicianList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from technicians where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Technician technician=new Technician();
                technician.setId(rs.getString("id"));
                technician.setFirst_name(rs.getString("first_name"));
                technician.setLast_name(rs.getString("last_name"));
                technician.setDate_of_birth(rs.getString("date_of_birth"));
                technician.setAddress(rs.getString("address"));
                technician.setPhone_no(rs.getString("phone_no"));
                technician.setEmail(rs.getString("email"));
                technician.setGender(rs.getString("gender"));
                technician.setDesignation(rs.getString("designation"));
                technician.setQualification(rs.getString("qualification"));
                technician.setSalary(rs.getInt("salary"));
                technicianList.add(technician);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllTechnician: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return technicianList;
    }

    public List<EmployeeSchedule> getAllSchedule(String employeeId){
        List<EmployeeSchedule> employeeScheduleList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from employee_schedule where employee_id='%s'",employeeId);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                EmployeeSchedule employeeSchedule=new EmployeeSchedule();
                employeeSchedule.setId(rs.getInt("id"));
                employeeSchedule.setEmployee_id(employeeId);
                employeeSchedule.setDay(rs.getString("day"));
                employeeSchedule.setStart_time(rs.getString("start_time"));
                employeeSchedule.setEnd_time(rs.getString("end_time"));

                employeeScheduleList.add(employeeSchedule);
            }
        } catch (Exception e) {
            System.out.println("Exception in getEmployeeSchedule: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return employeeScheduleList;
    }

    public void insertEmployeeSchedule(EmployeeSchedule employeeSchedule){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String insertQuery = String.format(
                    "insert into employee_schedule(employee_id,day,start_time,end_time)" +
                            " values ('%s', '%s', '%s', '%s')"
                    , employeeSchedule.getEmployee_id(),employeeSchedule.getDay(),
                    employeeSchedule.getStart_time(),employeeSchedule.getEnd_time());
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addEmployeeSchedule: " + e);
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

    public void deleteEmployeeSchedule(int id){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String deleteQuery=String.format("delete from employee_schedule where id='%d'",id);
            oc.updateDB(deleteQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in deleteEmployeeSchedule: " + e);
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
