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
                doctor.setLast_name(rs.getString("last_name"));
                doctor.setDate_of_birth(rs.getString("date_of_birth"));
                doctor.setAddress(rs.getString("address"));
                doctor.setPhone_no(rs.getString("phone_no"));
                doctor.setEmail(rs.getString("email"));
                doctor.setGender(rs.getString("gender"));
                doctor.setDepartment_name(rs.getString("department_name"));
                doctor.setDesignation(rs.getString("designation"));
                doctor.setQualification(rs.getString("qualification"));
                doctor.setChamber(rs.getString("chamber"));
                doctor.setVisiting_fee(rs.getInt("visiting_fee"));
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
                doctor.setFirst_name(rs.getString("first_name"));
                doctor.setLast_name(rs.getString("last_name"));
                doctor.setDate_of_birth(rs.getString("date_of_birth"));
                doctor.setAddress(rs.getString("address"));
                doctor.setPhone_no(rs.getString("phone_no"));
                doctor.setEmail(rs.getString("email"));
                doctor.setGender(rs.getString("gender"));
                doctor.setDesignation(rs.getString("designation"));
                doctor.setQualification(rs.getString("qualification"));
                doctor.setVisiting_fee(rs.getInt("visiting_fee"));
                doctor.setDepartment_name(rs.getString("department_name"));

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

    public List<DoctorSchedule> getAllSchedule(String doctorId){
        List<DoctorSchedule> doctorScheduleList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from doctor_schedule where doctor_id='%s'",doctorId);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                DoctorSchedule doctorSchedule=new DoctorSchedule();
                doctorSchedule.setId(rs.getInt("id"));
                doctorSchedule.setDoctor_id(doctorId);
                doctorSchedule.setSchedule_date(rs.getString("schedule_date"));
                doctorSchedule.setStart_time(rs.getString("start_time"));
                doctorSchedule.setEnd_time(rs.getString("end_time"));
                doctorSchedule.setPlace(rs.getString("place"));

                doctorScheduleList.add(doctorSchedule);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllSchedule: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return doctorScheduleList;
    }

    public DoctorSchedule getDoctorSchedule(int scheduleId){
        DoctorSchedule doctorSchedule=new DoctorSchedule();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from doctor_schedule where id=%d",scheduleId);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()){
                doctorSchedule.setId(rs.getInt("id"));
                doctorSchedule.setDoctor_id(rs.getString("doctor_id"));
                doctorSchedule.setSchedule_date(rs.getString("schedule_date"));
                doctorSchedule.setStart_time(rs.getString("start_time"));
                doctorSchedule.setEnd_time(rs.getString("end_time"));
                doctorSchedule.setPlace(rs.getString("place"));
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
        return doctorSchedule;
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

    public void deleteDoctorSchedule(int id){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String deleteQuery=String.format("delete from doctor_schedule where id='%d'",id);
            oc.updateDB(deleteQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in deleteDoctorSchedule: " + e);
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

    public void updateDoctor(String id,Doctor doctor){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "update doctors set address='%s',phone_no='%s',designation='%s'," +
                            "qualification=%s,chamber='%s',visiting_fee='%s'," +
                            "email='%s' where id='%s'",doctor.getAddress(),
                    doctor.getPhone_no(),doctor.getDesignation(),doctor.getQualification(),
                    doctor.getChamber(), doctor.getVisiting_fee(), doctor.getEmail(),id
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in updateDoctor: " + e);
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

    public List<Appointment> getAllAppointment(String doctorId){
        List<Appointment> appointmentList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from appointments where doctor_id='%s'",doctorId);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Appointment appointment=new Appointment();
                appointment.setAppointment_id(rs.getInt("appointment_id"));
                appointment.setDoctor_id(rs.getString("doctor_id"));
                appointment.setPatient_id(rs.getString("patient_id"));
                appointment.setSchedule_id(rs.getInt("schedule_id"));
                appointment.setAppointment_date(rs.getString("appointment_date"));
                appointment.setStatus(rs.getString("status"));

                Doctor doctor= getDoctor(appointment.getDoctor_id());
                DoctorSchedule doctorSchedule= getDoctorSchedule(appointment.getSchedule_id());
                appointment.setDoctor_name(doctor.getFirst_name()+" "+doctor.getLast_name());
                appointment.setVisiting_date(doctorSchedule.getSchedule_date());
                appointment.setVisiting_time(doctorSchedule.getStart_time()+" - "+doctorSchedule.getEnd_time());

                Patient patient=new PatientDao().getPatient(appointment.getPatient_id());


                appointmentList.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllAppointment: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appointmentList;
    }

    public Prescription getPrescription(int appointmentId){
        Prescription prescription=new Prescription();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from prescription where id=%d",appointmentId);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()){
                prescription.setAppointment_id(rs.getInt("appointment_id"));
                prescription.setProblem(rs.getString("problem"));
                prescription.setMedicine(rs.getString("medicine"));
                prescription.setTest(rs.getString("test"));
                prescription.setOperation(rs.getString("operation"));
                prescription.setProblem(rs.getString("others"));
            }
        } catch (Exception e) {
            System.out.println("Exception in getPrescription: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prescription;
    }

    public void updatePrescription(int appointmentId,Prescription prescription){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "update prescription set problem='%s',medicine='%s',test='%s'," +
                            "operation=%s,others='%s' where appointment_id=%d", prescription.getProblem(),
                    prescription.getMedicine(), prescription.getTest(), prescription.getOperation(),
                    prescription.getOthers()
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in updatePrescription: " + e);
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
