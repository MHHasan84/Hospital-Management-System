package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {
    DoctorDao doctorDao=new DoctorDao();
    PatientDao patientDao=new PatientDao();

    @GetMapping("/patient/{patient_id}/profile")
    public String profile(@PathVariable("patient_id") String patientId,Model model){
        Patient patient= patientDao.getPatient(patientId);
        model.addAttribute("patient",patient);
        model.addAttribute("patientId",patientId);
        return "patient_profile";
    }

    @GetMapping("/patient/{patient_id}/doctor_list")
    public String doctorList(@PathVariable("patient_id") String patientId,Model model){
        List<Doctor> doctorList=doctorDao.getAllDoctor();
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("patientId",patientId);
        return "patient_doctor_list";
    }

    @GetMapping("/patient/{patient_id}/doctor_schedule/{doctor_id}")
    public String doctorSchedule(@PathVariable("patient_id") String patientId,@PathVariable("doctor_id") String doctorId,Model model){
        List<DoctorSchedule> doctorScheduleList=doctorDao.getAllSchedule(doctorId);
        model.addAttribute("doctorScheduleList",doctorScheduleList);
        model.addAttribute("patientId",patientId);
        return "patient_doctor_schedule";
    }

    @GetMapping("/patient/{patient_id}/add_appointment/{doctor_id}/{schedule_id}")
    public String addAppointment(@PathVariable("patient_id") String patientId,@PathVariable("doctor_id") String doctorId,@PathVariable("schedule_id") int scheduleId,Model model){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDate= dtf.format(now);

        Appointment appointment=new Appointment();
        appointment.setDoctor_id(doctorId);
        appointment.setPatient_id(patientId);
        appointment.setSchedule_id(scheduleId);
        appointment.setAppointment_date(currentDate);
        appointment.setStatus("waiting for approval");

        patientDao.insertAppointment(appointment);
        model.addAttribute("patientId",patientId);
        return "redirect:/patient/"+patientId+"/appointments";
    }

    @GetMapping("/patient/{patient_id}/appointments")
    public String showAppointments(@PathVariable("patient_id") String patientId,Model model){
        List<Appointment> appointmentList=patientDao.getAllAppointments(patientId);
        model.addAttribute("appointmentList",appointmentList);
        model.addAttribute("patientId",patientId);
        return "patient_appointments";
    }

    @GetMapping("/patient/{patient_id}/prescription/{appointment_id}")
    public String showPrescription(@PathVariable("patient_id") String patientId,@PathVariable("appointment_id") int appointmentId,Model model){
        Prescription prescription= patientDao.getPrescription(appointmentId);
        model.addAttribute("prescription",prescription);
        model.addAttribute("patientId",patientId);
        return "patient_prescription";
    }

    @GetMapping("/patient/{patient_id}/test")
    public String showTest(@PathVariable("patient_id") String patientId,Model model){
        List<PatientTest> patientTestList=patientDao.getAllTest(patientId);
        model.addAttribute("patientTestList",patientTestList);
        model.addAttribute("patientId",patientId);
        return "patient_test";
    }

    @GetMapping("/patient/{patient_id}/operation")
    public String showOperation(@PathVariable("patient_id") String patientId,Model model){
        List<PatientOperation> patientOperationList=patientDao.getAllPatientOperation(patientId);
        model.addAttribute("patientOperationList",patientOperationList);
        model.addAttribute("patientId",patientId);
        return "patient_operation";
    }

    @GetMapping("/patient/{patient_id}/admittedHistory")
    public String showAdmittedHistory(@PathVariable("patient_id") String patientId,Model model){
        List<AdmittedPatient> admittedPatientList=patientDao.getAllAdmittedPatient(patientId);
        model.addAttribute("admittedPatientList",admittedPatientList);
        model.addAttribute("patientId",patientId);
        return "patient_admitted_history";
    }

    @GetMapping("/patient/{patient_id}/bill")
    public String showBill(@PathVariable("patient_id") String patientId,Model model){
        List<Bill> billList=patientDao.getAllBill(patientId);
        model.addAttribute("billList",billList);
        model.addAttribute("patientId",patientId);
        return "patient_bill";
    }

}
