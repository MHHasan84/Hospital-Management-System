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
    TechnitianDao technitianDao=new TechnitianDao();
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
            return "redirect:/admin/dashboard";
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


    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model){
        return "admin_dashboard";
    }

    @GetMapping("/admin/user/doctor")
    public String adminUserDoctor(Model model){
        List<Doctor> doctorList=doctorDao.getAllDoctor();
        model.addAttribute("doctor_list",doctorList);
        model.addAttribute("user",new User());
        return "admin_user_doctor";
    }

    @PostMapping("/admin/user/doctor/search")
    public String adminUserDoctorBySearch(User user,Model model){
        List<Doctor> doctorList=doctorDao.getAllDoctorById(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("doctor_list",doctorList);
        System.out.println(user.getId());
        return "admin_user_doctor";
    }

     @GetMapping("/admin/add_doctor_form")
     public String adminAddDoctorForm(Model model){
        model.addAttribute("doctor",new Doctor());
        return "admin_user_doctor_addnew";
     }

    @PostMapping("/admin/add_doctor")
    public String adminAddDoctor(Doctor doctor,Model model) throws ParseException {
        System.out.println("hasan");
        model.addAttribute("doctor",doctor);
        Date date=new SimpleDateFormat("MM-dd-yyyy").parse(doctor.getDate_of_birth());
        System.out.println(date);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = dateFormat.format(date);
        System.out.println(strDate);
        doctorDao.insertDoctor(doctor);
        //return "admin_user_doctor";
        return "redirect:/admin/user/doctor";
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
