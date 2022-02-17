package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReceptionistDao {
    public void insertReceptionist(Receptionist receptionist){
        UserDao2 userDao2=new UserDao2();
        boolean user_insert_succeed=userDao2.addUser(receptionist.getId(), receptionist.getPassword(), "4");
        if(!user_insert_succeed){
            return;
        }
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();

            String insertQuery = String.format(
                    "insert into receptionist(id,first_name,last_name,address,gender,phone_no,designation," +
                            "qualification,email,date_of_birth,salary) values ('%s', '%s', '%s'," +
                            " '%s', '%s', '%s', '%s', '%s', '%s','%s',%d)"
                    , receptionist.getId(), receptionist.getFirst_name(), receptionist.getLast_name(), receptionist.getAddress(),
                    receptionist.getGender(), receptionist.getPhone_no(), receptionist.getDesignation(), receptionist.getQualification(),
                    receptionist.getEmail(), receptionist.getDate_of_birth(), receptionist.getSalary());
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addReceptionist: " + e);
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
    public Receptionist getReceptionist(String id){
        Receptionist receptionist=new Receptionist();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from receptionist where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                receptionist.setId(rs.getString("id"));
                receptionist.setFirst_name(rs.getString("first_name"));
                receptionist.setLast_name(rs.getString("last_name"));
                receptionist.setDate_of_birth(rs.getString("date_of_birth"));
                receptionist.setAddress(rs.getString("address"));
                receptionist.setPhone_no(rs.getString("phone_no"));
                receptionist.setEmail(rs.getString("email"));
                receptionist.setGender(rs.getString("gender"));
                receptionist.setDesignation(rs.getString("designation"));
                receptionist.setQualification(rs.getString("qualification"));
                receptionist.setSalary(rs.getInt("salary"));
            }
            else {
                System.out.println("No receptionist in this id");
            }
        } catch (Exception e) {
            System.out.println("Exception in getReceptionist: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return receptionist;
    }

    public List<Receptionist> getAllReceptionist(){
        List<Receptionist> receptionistList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from receptionist";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Receptionist receptionist=new Receptionist();
                receptionist.setId(rs.getString("id"));
                receptionist.setFirst_name(rs.getString("first_name"));
                receptionist.setLast_name(rs.getString("last_name"));
                receptionist.setDate_of_birth(rs.getString("date_of_birth"));
                receptionist.setAddress(rs.getString("address"));
                receptionist.setPhone_no(rs.getString("phone_no"));
                receptionist.setEmail(rs.getString("email"));
                receptionist.setGender(rs.getString("gender"));
                receptionist.setDesignation(rs.getString("designation"));
                receptionist.setQualification(rs.getString("qualification"));
                receptionist.setSalary(rs.getInt("salary"));
                receptionistList.add(receptionist);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllReceptionist: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return receptionistList;
    }

    public List<Receptionist> getAllReceptionistById(String id){
        List<Receptionist> receptionistList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from receptionist where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Receptionist receptionist=new Receptionist();
                receptionist.setId(rs.getString("id"));
                receptionist.setFirst_name(rs.getString("first_name"));
                receptionist.setLast_name(rs.getString("last_name"));
                receptionist.setDate_of_birth(rs.getString("date_of_birth"));
                receptionist.setAddress(rs.getString("address"));
                receptionist.setPhone_no(rs.getString("phone_no"));
                receptionist.setEmail(rs.getString("email"));
                receptionist.setGender(rs.getString("gender"));
                receptionist.setDesignation(rs.getString("designation"));
                receptionist.setQualification(rs.getString("qualification"));
                receptionist.setSalary(rs.getInt("salary"));
                receptionistList.add(receptionist);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllReceptionist: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return receptionistList;
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
