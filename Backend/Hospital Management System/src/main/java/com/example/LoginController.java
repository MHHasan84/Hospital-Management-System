package com.example;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {
    UserDao2 userDao2=new UserDao2();
    DoctorDao doctorDao=new DoctorDao();
    PatientDao patientDao=new PatientDao();
    TechnicianDao technicianDao=new TechnicianDao();
    AdminDao adminDao=new AdminDao();
    @GetMapping("/users/show/{id}")
    public String showLogin(@PathVariable("id") String id, Model model){
        User user= userDao2.getUser(id);
        System.out.println(user.getId());
        model.addAttribute("user",user);
        return "temp";
    }
    @PostMapping("/users/login")
    public String login(User user,Model model){
        model.addAttribute("user",user);
        User user1=userDao2.getUser(user.getId());
        if(user1==null){
            return "login_form";
        }
        if(!user1.getPassword().equals(user.getPassword())){
            return "login_form";
        }
        if(user1.getUsertype().equals("0")){
            return "redirect:/admin/user/doctor";
        }
        if(user1.getUsertype().equals("1")){
            return "redirect:/doctor/"+user.getId()+"/profile";
        }
        if(user1.getUsertype().equals("2")){
            return "redirect:/patient/"+user.getId()+"/profile";
        }
        if(user1.getUsertype().equals("3")){
            return "redirect:/technician/"+user.getId()+"/profile";
        }
        if(user1.getUsertype().equals("4")){
            return "redirect:/receptionist/"+user.getId()+"/profile";
        }
        return "login_form";
    }

    @GetMapping("/login")
    public String loginForm(User user){
        return "login_form";
    }

    @GetMapping("/sign_up/form")
    public String signUpForm(Model model){
        model.addAttribute("patient",new Patient());
        return "sign_up_form";
    }

    @PostMapping("/sign_up")
    public String signUp(Patient patient){
        userDao2.insertPatient(patient);
        return "redirect:/login";
    }


    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model){
        return "admin_dashboard";
    }


}
