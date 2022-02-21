package com.example;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {
    DoctorDao doctorDao=new DoctorDao();
    TestDao testDao=new TestDao();
    OperationDao operationDao=new OperationDao();
    public Patient getPatient(String id){
        Patient patient=new Patient();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from patients where id='%s'",id);
            ResultSet rs = oc.searchDB(query);
            if (rs.next()) {
                patient.setId(rs.getString("id"));
            }
            else {
                System.out.println("No patient in this id");
            }
        } catch (Exception e) {
            System.out.println("Exception in getPatient: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patient;
    }

    public void insertAppointment(Appointment appointment){
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String insertQuery=String.format(
                    "insert into appointments(doctor_id,patient_id,schedule_id," +
                            "appointment_date,status,prescription_id)" +
                            " values('%s','%s','%d','%s','%s')", appointment.getDoctor_id(),
                    appointment.getPatient_id(), appointment.getSchedule_id(), appointment.getAppointment_date(),
                    appointment.getStatus()
            );
            oc.updateDB(insertQuery);
        }
        catch (Exception e) {
            System.out.println("Exception in addAppointment: " + e);
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

    public List<Appointment> getAllAppointments(String patientId){
        List<Appointment> appointmentList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from appointments where patient_id='%s'",patientId);
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

    public List<PatientTest> getAllTest(String patientId){
        List<PatientTest> patientTestList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from patient_test where patient_id='%s'",patientId);
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
            System.out.println("Exception in getAllTestByPatientId: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patientTestList;
    }

    public List<PatientOperation> getAllPatientOperation(String patientId){
        List<PatientOperation> patientOperationList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from patient_operation where patient_id='%s'",patientId);
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
                Patient patient= getPatient(patientOperation.getPatient_id());
                Operation operation= operationDao.getOperation(patientOperation.getOperation_id());

                patientOperation.setDoctor_name(doctor.getFirst_name()+" "+doctor.getLast_name());
                patientOperation.setPatient_name(patient.getFirst_name()+" "+patient.getLast_name());
                patientOperation.setOperation_name(operation.getOperation_name());

                patientOperationList.add(patientOperation);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllPatientOperationByPatientId: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return patientOperationList;
    }

    public List<AdmittedPatient> getAllAdmittedPatient(String patientId){
        List<AdmittedPatient> admittedPatientList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from admitted_patient where patient_id='%s'",patientId);
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
                Patient patient= getPatient(admittedPatient.getPatient_id());

                admittedPatient.setDoctor_name(doctor.getFirst_name()+" "+doctor.getLast_name());
                admittedPatient.setPatient_name(patient.getFirst_name()+" "+patient.getLast_name());


                admittedPatientList.add(admittedPatient);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllAdmittedPatientByPatientId: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return admittedPatientList;
    }

    public List<Bill> getAllBill(String patientId){
        List<Bill> billList=new ArrayList<>();
        OracleConnect oc = null;
        try {
            oc = new OracleConnect();
            String query = String.format("select * from bill where patient_id='%s'",patientId);
            ResultSet rs = oc.searchDB(query);
            while (rs.next()){
                Bill bill=new Bill();
                bill.setBill_id(rs.getInt("bill_id"));
                bill.setBill_type(rs.getString("bill_type"));
                bill.setBill_create_date(rs.getString("bill_create_date"));
                bill.setBill_clear_date(rs.getString("bill_clear_date"));
                bill.setBill_status(rs.getString("bill_status"));
                bill.setPatient_id(rs.getString("patient_id"));


                billList.add(bill);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllBillByPatientId: " + e);
        } finally {
            try {
                oc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return billList;
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
}
