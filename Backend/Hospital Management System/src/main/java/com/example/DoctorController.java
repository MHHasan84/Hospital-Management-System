package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {
    @GetMapping("/doctor_profile")
    public String showDoctorProfile(){
        UserDao2 userDao2 =new UserDao2();
        userDao2.listUsers();
        return "doctor_profile";
    }
}
