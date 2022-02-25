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

    public List<DoctorSchedule> getAllScheduleForChamber(String doctorId){
        List<DoctorSchedule> doctorScheduleList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from doctor_schedule where doctor_id='%s' and place='Chamber'",doctorId);
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
                            "qualification='%s',chamber='%s',visiting_fee=%d," +
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
                appointment.setPatientName(patient.getFirst_name()+" "+patient.getLast_name());


                appointmentList.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllAppointmentByDoctorId: " + e);
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
            String query = String.format("select * from prescription where appointment_id=%d",appointmentId);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()){
                prescription.setAppointment_id(rs.getInt("appointment_id"));
                prescription.setProblem(rs.getString("problem"));
                prescription.setMedicine(rs.getString("medicine"));
                prescription.setTest(rs.getString("test"));
                prescription.setOperation(rs.getString("operation"));
                prescription.setOthers(rs.getString("others"));
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
            String updateQuery1=String.format(
                    "update appointments set status='visited'" +
                            " where appointment_id=%d",appointmentId
            );
            oc.updateDB(updateQuery1);

            String query = String.format("select * from prescription where appointment_id=%d",appointmentId);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()){
                String updateQuery=String.format(
                        "update prescription set problem='%s',medicine='%s',test='%s'," +
                                "operation='%s',others='%s' where appointment_id=%d", prescription.getProblem(),
                        prescription.getMedicine(), prescription.getTest(), prescription.getOperation(),
                        prescription.getOthers(),appointmentId
                );
                oc.updateDB(updateQuery);
            }
            else{
                String insertQuery = String.format(
                        "insert into prescription(appointment_id,problem,medicine,test," +
                                "operation,others) values (%d,'%s', '%s', '%s', '%s', '%s')"
                        ,prescription.getAppointment_id(), prescription.getProblem(), prescription.getMedicine(), prescription.getTest(),
                        prescription.getOperation(), prescription.getOthers());
                oc.updateDB(insertQuery);
            }
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

    public List<PatientTest> getAllTest(String doctorId){
        List<PatientTest> patientTestList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from patient_test where doctor_id='%s'",doctorId);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                PatientTest patientTest=new PatientTest();
                patientTest.setPatient_id(rs.getString("patient_id"));
                patientTest.setDoctor_id(rs.getString("doctor_id"));
                patientTest.setTest_id(rs.getString("test_id"));
                patientTest.setTest_date(rs.getString("test_date"));
                patientTest.setResult_date(rs.getString("result_date"));
                patientTest.setResult(rs.getString("result"));
                patientTest.setSample_no(rs.getInt("sample_no"));


                patientTest.setTest_name(new TestDao().getTest(patientTest.getTest_id()).getTest_name());

                patientTestList.add(patientTest);

            }
        } catch (Exception e) {
            System.out.println("Exception in getAllTestByDoctorId: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patientTestList;
    }

    public List<PatientOperation> getAllPatientOperation(String doctorId){
        List<PatientOperation> patientOperationList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from patient_operation where doctor_id='%s'",doctorId);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                PatientOperation patientOperation=new PatientOperation();
                patientOperation.setOperation_no(rs.getInt("operation_no"));
                patientOperation.setPatient_id(rs.getString("patient_id"));
                patientOperation.setDoctor_id(rs.getString("doctor_id"));
                patientOperation.setOperation_date(rs.getString("operation_date"));
                patientOperation.setOperation_time(rs.getString("operation_time"));
                patientOperation.setOperation_id(rs.getString("operation_id"));
                patientOperation.setResult(rs.getString("result"));

                Doctor doctor= getDoctor(patientOperation.getDoctor_id());
                Patient patient= new PatientDao().getPatient(patientOperation.getPatient_id());
                Operation operation= new OperationDao().getOperation(patientOperation.getOperation_id());

                patientOperation.setDoctor_name(doctor.getFirst_name()+" "+doctor.getLast_name());
                patientOperation.setPatient_name(patient.getFirst_name()+" "+patient.getLast_name());
                patientOperation.setOperation_name(operation.getOperation_name());

                patientOperationList.add(patientOperation);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllPatientOperationByDoctorId: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patientOperationList;
    }

    public List<AdmittedPatient> getAllAdmittedPatient(String doctorId){
        List<AdmittedPatient> admittedPatientList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from admitted_patient where doctor_id='%s'",doctorId);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                AdmittedPatient admittedPatient=new AdmittedPatient();
                admittedPatient.setAdmitted_id(rs.getInt("admitted_id"));
                admittedPatient.setAdmitted_date(rs.getString("admitted_date"));
                admittedPatient.setPatient_id(rs.getString("patient_id"));
                admittedPatient.setDoctor_id(rs.getString("doctor_id"));
                admittedPatient.setRelease_date(rs.getString("release_date"));
                admittedPatient.setWard_no(rs.getString("ward_no"));
                admittedPatient.setBed_no(rs.getString("bed_no"));

                Doctor doctor= getDoctor(admittedPatient.getDoctor_id());
                Patient patient= new PatientDao().getPatient(admittedPatient.getPatient_id());

                admittedPatient.setDoctor_name(doctor.getFirst_name()+" "+doctor.getLast_name());
                admittedPatient.setPatient_name(patient.getFirst_name()+" "+patient.getLast_name());


                admittedPatientList.add(admittedPatient);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllAdmittedPatientByDoctorId: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return admittedPatientList;
    }

    public PatientOperation getPatientOperation(int operationNo){
        PatientOperation patientOperation=new PatientOperation();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from patient_operation where operation_no=%d",operationNo);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()){
                patientOperation.setOperation_no(rs.getInt("operation_no"));
                patientOperation.setPatient_id(rs.getString("patient_id"));
                patientOperation.setDoctor_id(rs.getString("doctor_id"));
                patientOperation.setOperation_date(rs.getString("operation_date"));
                patientOperation.setOperation_time(rs.getString("operation_time"));
                patientOperation.setOperation_id(rs.getString("operation_id"));
                patientOperation.setResult(rs.getString("result"));

                Doctor doctor= getDoctor(patientOperation.getDoctor_id());
                Patient patient= new PatientDao().getPatient(patientOperation.getPatient_id());
                Operation operation= new OperationDao().getOperation(patientOperation.getOperation_id());

                patientOperation.setDoctor_name(doctor.getFirst_name()+" "+doctor.getLast_name());
                patientOperation.setPatient_name(patient.getFirst_name()+" "+patient.getLast_name());
                patientOperation.setOperation_name(operation.getOperation_name());

            }
        } catch (Exception e) {
            System.out.println("Exception in getAllPatientOperationByOperationNo: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patientOperation;
    }

    public void editOperationResult(int operationNo,PatientOperation patientOperation){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "update patient_operation set result='%s' where operation_no=%d",
                    patientOperation.getResult(),operationNo
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in editOperationResult: " + e);
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
