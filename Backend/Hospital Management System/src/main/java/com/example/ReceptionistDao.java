package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReceptionistDao {
    DoctorDao doctorDao=new DoctorDao();
    PatientDao patientDao=new PatientDao();
    OperationDao operationDao=new OperationDao();
    TestDao testDao=new TestDao();
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

    public void insertPatientTest(PatientTest patientTest){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();

            String insertQuery = String.format(
                    "insert into patient_test(patient_id,doctor_id,test_id,test_date) values ('%s', '%s', '%s'," +
                            " '%s')"
                    , patientTest.getPatient_id(), patientTest.getDoctor_id(), patientTest.getTest_id(),
                    patientTest.getTest_date());
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addPatientTest: " + e);
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

    public void insertPatientOperation(PatientOperation patientOperation){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();

            String insertQuery = String.format(
                    "insert into patient_operation(operation_id,doctor_id,patient_id,operation_date,operation_time) values ('%s', '%s', '%s'," +
                            " '%s','%s')"
                    , patientOperation.getOperation_id(), patientOperation.getDoctor_id(),patientOperation.getPatient_id(),
                    patientOperation.getOperation_date(),patientOperation.getOperation_time());
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addPatientOperation: " + e);
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

    public List<PatientOperation> getAllPatientOperation(){
        List<PatientOperation> patientOperationList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from patient_operation";
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

                Doctor doctor= doctorDao.getDoctor(patientOperation.getDoctor_id());
                Patient patient= patientDao.getPatient(patientOperation.getPatient_id());
                Operation operation= operationDao.getOperation(patientOperation.getOperation_id());

                patientOperation.setDoctor_name(doctor.getFirst_name()+" "+doctor.getLast_name());
                patientOperation.setPatient_name(patient.getFirst_name()+" "+patient.getLast_name());
                patientOperation.setOperation_name(operation.getOperation_name());

                patientOperationList.add(patientOperation);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllPatientOperation: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patientOperationList;
    }

    public void insertAdmittedPatient(AdmittedPatient admittedPatient){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();

            String insertQuery = String.format(
                    "insert into admitted_patient(doctor_id,patient_id,ward_no,bed_no,admitted_date) values ('%s', '%s', '%s'," +
                            " '%s','%s')"
                    , admittedPatient.getDoctor_id(),admittedPatient.getPatient_id(),admittedPatient.getWard_no(),admittedPatient.getBed_no(),
                    admittedPatient.getAdmitted_date());
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addAddAdmittedPatient: " + e);
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

    public List<AdmittedPatient> getAllAdmittedPatient(){
        List<AdmittedPatient> admittedPatientList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from admitted_patient";
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

                Doctor doctor= doctorDao.getDoctor(admittedPatient.getDoctor_id());
                Patient patient= patientDao.getPatient(admittedPatient.getPatient_id());

                admittedPatient.setDoctor_name(doctor.getFirst_name()+" "+doctor.getLast_name());
                admittedPatient.setPatient_name(patient.getFirst_name()+" "+patient.getLast_name());


                admittedPatientList.add(admittedPatient);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllAdmittedPatient: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return admittedPatientList;
    }

    public void setReleaseDate(int admittedId,String date){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "update admitted_patient set release_date='%s'" +
                            " where admitted_id=%d",date,admittedId
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in updateReleaseDate: " + e);
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
    public List<Appointment> getAllAppointment(){
        List<Appointment> appointmentList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from appointments";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Appointment appointment=new Appointment();
                appointment.setAppointment_id(rs.getInt("appointment_id"));
                appointment.setDoctor_id(rs.getString("doctor_id"));
                appointment.setPatient_id(rs.getString("patient_id"));
                appointment.setSchedule_id(rs.getInt("schedule_id"));
                appointment.setAppointment_date(rs.getString("appointment_date"));
                appointment.setStatus(rs.getString("status"));

                Doctor doctor= doctorDao.getDoctor(appointment.getDoctor_id());
                DoctorSchedule doctorSchedule= doctorDao.getDoctorSchedule(appointment.getSchedule_id());
                appointment.setDoctor_name(doctor.getFirst_name()+" "+doctor.getLast_name());
                appointment.setVisiting_date(doctorSchedule.getSchedule_date());
                appointment.setVisiting_time(doctorSchedule.getStart_time()+" - "+doctorSchedule.getEnd_time());

                Patient patient=new PatientDao().getPatient(appointment.getPatient_id());
                appointment.setPatientName(patient.getFirst_name()+" "+patient.getLast_name());


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

    public List<Bill> getAllBill(){
        List<Bill> billList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = "select * from bill";
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Bill bill=new Bill();
                bill.setBill_id(rs.getInt("bill_id"));
                bill.setBill_type(rs.getString("bill_type"));
                bill.setBill_create_date(rs.getString("bill_create_date"));
                bill.setBill_clear_date(rs.getString("bill_clear_date"));
                bill.setBill_status(rs.getString("bill_status"));

                billList.add(bill);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllBill: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return billList;
    }

    public void clearBill(int billId){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "update bill set bill_status='clear'" +
                            " where bill_id=%d",billId
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in updateBillClear: " + e);
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

    public void approveAppointment(int appointmentId){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "update appointments set status='approved'" +
                            " where appointment_id=%d",appointmentId
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in updateApproveAppointment: " + e);
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

    public void deleteAppointment(int appointmentId){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String updateQuery=String.format(
                    "delete from appointments" +
                            " where appointment_id=%d",appointmentId
            );
            oc.updateDB(updateQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in deleteAppointment: " + e);
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
