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
    @GetMapping("/doctor/profile")
    public String showDoctorProfile(){
        UserDao2 userDao2 =new UserDao2();
        userDao2.listUsers();
        return "doctor_profile";
    }
    @GetMapping("/doctor/appointments/{doctor_id}")
    public String doctorAppointments(@PathVariable("doctor_id") String doctorId, Model model){
        List<Appointment> appointmentList=doctorDao.getAllAppointment(doctorId);
        model.addAttribute("appointmentList",appointmentList);
        return "doctor_appointments";
    }
    @GetMapping("/doctor/appointments/{doctor_id}/prescription/{appointment_id}")
    public String doctorPrescription(@PathVariable("doctor_id") String doctorId,@PathVariable("appointment_id") int appointmentId,Model model){
        Prescription prescription= doctorDao.getPrescription(appointmentId);
        model.addAttribute("prescription",prescription);
        model.addAttribute("doctorId",doctorId);
        model.addAttribute("appointmentId",appointmentId);
        return "doctor_prescription";
    }
    @PostMapping("/doctor/appointments/{doctor_id}/make_prescription/{appointment_id}")
    public String doctorMakePrescription(@PathVariable("doctor_id") String doctorId,@PathVariable("appointment_id") int appointmentId,Prescription prescription){
        doctorDao.updatePrescription(appointmentId,prescription);
        return "redirect:/docotr/appointments/"+doctorId;
    }

}
