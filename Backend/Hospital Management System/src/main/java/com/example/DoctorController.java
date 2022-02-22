package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DoctorController {
    DoctorDao doctorDao=new DoctorDao();
    @GetMapping("/doctor/{doctor_id}/profile")
    public String showDoctorProfile(@PathVariable("doctor_id") String doctorId, Model model){
        model.addAttribute("doctorId",doctorId);
        Doctor doctor=doctorDao.getDoctor(doctorId);
        model.addAttribute("doctor",doctor);
        return "doctor_profile";
    }
    @GetMapping("/doctor/{doctor_id}/appointments")
    public String doctorAppointments(@PathVariable("doctor_id") String doctorId, Model model){
        model.addAttribute("doctorId",doctorId);
        List<Appointment> appointmentList=doctorDao.getAllAppointment(doctorId);
        model.addAttribute("appointmentList",appointmentList);
        return "doctor_appointments";
    }
    @GetMapping("/doctor/{doctor_id}/prescription/{appointment_id}")
    public String doctorPrescription(@PathVariable("doctor_id") String doctorId,@PathVariable("appointment_id") int appointmentId,Model model){
        model.addAttribute("doctorId",doctorId);
        Prescription prescription= doctorDao.getPrescription(appointmentId);
        model.addAttribute("prescription",prescription);
        model.addAttribute("appointmentId",appointmentId);
        return "doctor_prescription";
    }
    @PostMapping("/doctor/{doctor_id}/make_prescription/{appointment_id}")
    public String doctorMakePrescription(@PathVariable("doctor_id") String doctorId,@PathVariable("appointment_id") int appointmentId,Prescription prescription){
        doctorDao.updatePrescription(appointmentId,prescription);
        return "redirect:/doctor/"+doctorId+"/appointments";
    }
    @GetMapping("/doctor/{doctor_id}/schedule")
    public String doctorSchedule(@PathVariable("doctor_id") String doctorId, Model model){
        model.addAttribute("doctorId",doctorId);
        List<DoctorSchedule> doctorScheduleList=doctorDao.getAllSchedule(doctorId);
        model.addAttribute("doctorScheduleList",doctorScheduleList);
        return "doctor_schedule";
    }

    @GetMapping("/doctor/{doctor_id}/operation")
    public String doctorOperation(@PathVariable("doctor_id") String doctorId, Model model){
        model.addAttribute("doctorId",doctorId);
        List<PatientOperation> patientOperationList=doctorDao.getAllPatientOperation(doctorId);
        model.addAttribute("patientOperationList",patientOperationList);
        return "doctor_operation";
    }

    @GetMapping("/doctor/{doctor_id}/operation_result/{operation_no}")
    public String doctorOperationResult(@PathVariable("doctor_id") String doctorId,@PathVariable("operation_no") int operationNo, Model model){
        model.addAttribute("doctorId",doctorId);
        model.addAttribute("operationNo",operationNo);
        PatientOperation patientOperation=doctorDao.getPatientOperation(operationNo);
        model.addAttribute("patientOperation", patientOperation);
        return "doctor_operation_result";
    }

    @PostMapping("/doctor/{doctor_id}/make_operation_result/{operation_no}")
    public String doctorMakeOperationResult(@PathVariable("doctor_id") String doctorId,@PathVariable("operation_no") int operationNo, PatientOperation patientOperation){
        doctorDao.editOperationResult(operationNo,patientOperation);
        return "redirect:/doctor/"+doctorId+"/operation";
    }

    @GetMapping("/doctor/{doctor_id}/test")
    public String doctorTest(@PathVariable("doctor_id") String doctorId, Model model){
        model.addAttribute("doctorId",doctorId);
        List<PatientTest> patientTestList=doctorDao.getAllTest(doctorId);
        model.addAttribute("patientTestList",patientTestList);
        return "doctor_test";
    }

    @GetMapping("/doctor/{doctor_id}/admitted_patient")
    public String doctorAdmittedPatient(@PathVariable("doctor_id") String doctorId, Model model){
        model.addAttribute("doctorId",doctorId);
        List<AdmittedPatient> admittedPatientList=doctorDao.getAllAdmittedPatient(doctorId);
        model.addAttribute("admittedPatientList",admittedPatientList);
        return "doctor_admitted_patient";
    }
}
