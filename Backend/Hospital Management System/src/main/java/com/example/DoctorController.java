package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DoctorController {
    @GetMapping("/doctor/profile")
    public String showDoctorProfile(){
        UserDao2 userDao2 =new UserDao2();
        userDao2.listUsers();
        return "doctor_profile";
    }
    @GetMapping("/doctor/appointments/{doctor_id}")
    public String doctorAppointments(@PathVariable("doctor_id") String doctorId, Model model){

    }
}
