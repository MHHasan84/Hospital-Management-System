package com.example;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    UserDao2 userDao2=new UserDao2();
    DoctorDao doctorDao=new DoctorDao();
    PatientDao patientDao=new PatientDao();
    TechnitianDao technitianDao=new TechnitianDao();
    @GetMapping("/users/show/{id}")
    public String showLogin(@PathVariable("id") String id, Model model){
        User user= userDao2.getUser(id);
        System.out.println(user.getId());
        model.addAttribute("user",user);
        return "temp";
    }
    @PostMapping("/users/login")
    public String login(User user){
        User user1=userDao2.getUser(user.getId());
        if(user1==null){
            return "login_form";
        }
        if(!user1.getPassword().equals(user.getPassword())){
            return "login_form";
        }
        if(user1.getUsertype().equals("1")){
            return "redirect:/doctor/profile/"+user.getId();
        }
        if(user1.getUsertype().equals("2")){
            return "redirect:/patient/profile/"+user.getId();
        }
        if(user1.getUsertype().equals("3")){
            return "redirect:/technitian/profile/"+user.getId();
        }
        return "login_form";
    }

    @GetMapping("/login")
    public String loginForm(User user){
        return "login_form";
    }


    @GetMapping("/doctor/profile/{id}")
    public String doctorProfile(@PathVariable("id") String id, Model model){
        Doctor doctor= doctorDao.getDoctor(id);
        model.addAttribute("doctor",doctor);
        return "doctor_profile";
    }

    @GetMapping("/patient/profile/{id}")
    public String patientProfile(@PathVariable("id") String id, Model model){
        Patient patient= patientDao.getPatient(id);
        model.addAttribute("patient",patient);
        return "patient_profile";
    }

    @GetMapping("/technitian/profile/{id}")
    public String technitianProfile(@PathVariable("id") String id, Model model){
        Technitian technitian= technitianDao.getTechnitian(id);
        model.addAttribute("technitian",technitian);
        return "technitian_profile";
    }
}
