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

}
