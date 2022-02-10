package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao {
    public boolean insertDoctor(Doctor doctor){
        boolean doctor_insert_succeed=false;
        UserDao2 userDao2=new UserDao2();
        boolean user_insert_succeed=userDao2.addUser(doctor.getId(), doctor.getPassword(), "1");
        if(!user_insert_succeed){
            return false;
        }
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();

            String insertQuery = String.format(
                    "insert into doctors(id,first_name,last_name,address,gender,phone_no,designation," +
                            "qualification,chamber,visiting_fee,email,department_name,date_of_birth) values ('%s', '%s', '%s'," +
                            " '%s', '%s', '%s', '%s', '%s', '%s',%d,'%s','%s',to_date('%s','yyyy-mm-dd'))"
                    , doctor.getId(),doctor.getFirst_name(),doctor.getLast_name(),doctor.getAddress(),
                    doctor.getGender(),doctor.getPhone_no(),doctor.getDesignation(),doctor.getQualification(),
                    doctor.getChamber(),doctor.getVisiting_fee(),doctor.getEmail(),doctor.getDepartment_name(),
                    doctor.getDate_of_birth());
            oc.updateDB(insertQuery);
            doctor_insert_succeed=true;
        }
        catch (Exception e) {
            System.out.println("Exception in addUser: " + e);
        }
        finally {
            try {
                oc.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doctor_insert_succeed;
    }
    public Doctor getDoctor(String id){
        Doctor doctor=new Doctor();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from doctors where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                doctor.setId(rs.getString("id"));
                doctor.setFirst_name(rs.getString("first_name"));
                doctor.setDate_of_birth(String.valueOf(rs.getDate("date_of_birth")));
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

    public List<Doctor> getAllDoctor(){
        List<Doctor> doctorList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from doctors";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Doctor doctor=new Doctor();
                doctor.setId(rs.getString("id"));
                doctorList.add(doctor);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllDoctor: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doctorList;
    }

    public List<Doctor> getAllDoctorById(String id){
        List<Doctor> doctorList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from doctors where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Doctor doctor=new Doctor();
                doctor.setId(rs.getString("id"));
                doctorList.add(doctor);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllDoctor: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doctorList;
    }

    public List<DoctorSchedule> getAllSchedule(int doctorId){
        List<DoctorSchedule> doctorScheduleList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from doctor_schedule where id='%s'",doctorId);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                DoctorSchedule doctorSchedule=new DoctorSchedule();

                doctorSchedule.setSchedule_date(rs.getString("schedule_date"));
                doctorSchedule.setStart_time(rs.getString("start_time"));
                doctorSchedule.setEnd_time(rs.getString("end_time"));
                doctorSchedule.setPlace(rs.getString("place"));

                doctorScheduleList.add(doctorSchedule);
            }
        } catch (Exception e) {
            System.out.println("Exception in getDoctorSchedule: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doctorScheduleList;
    }

    public void insertDoctorSchedule(DoctorSchedule doctorSchedule){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String insertQuery = String.format(
                    "insert into doctor_schedule(doctor_id,schedule_date,start_time,end_time," +
                            "place) values ('%s', '%s', '%s', '%s', '%s')"
                    , doctorSchedule.getDoctor_id(),doctorSchedule.getSchedule_date(),
                    doctorSchedule.getStart_time(),doctorSchedule.getEnd_time(),
                    doctorSchedule.getPlace());
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addDoctorSchedule: " + e);
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
