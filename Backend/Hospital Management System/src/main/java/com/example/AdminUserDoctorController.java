package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.List;

@Controller
public class AdminUserDoctorController {
    DoctorDao doctorDao=new DoctorDao();
    AdminDao adminDao=new AdminDao();

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
        model.addAttribute("doctor_list",doctorList);
        return "admin_user_doctor";
    }

    @GetMapping("/admin/add_doctor_form")
    public String adminAddDoctorForm(Model model){
        model.addAttribute("doctor",new Doctor());
        List<Department> departmentList=adminDao.getAllDepartment();
        List<Room> roomList=adminDao.getRoomListForChamber();
        model.addAttribute("departmentList",departmentList);
        model.addAttribute("roomList",roomList);
        return "admin_user_doctor_addnew";
    }

    @PostMapping("/admin/add_doctor")
    public String adminAddDoctor(Doctor doctor) throws ParseException {
        doctorDao.insertDoctor(doctor);
        return "redirect:/admin/user/doctor";
    }

    @GetMapping("/admin/doctor/schedule/{id}")
    public String adminDoctorSchedule(@PathVariable("id") String id, Model model){
        List<DoctorSchedule> doctorScheduleList=doctorDao.getAllSchedule(id);
        model.addAttribute("doctorScheduleList",doctorScheduleList);
        model.addAttribute("doctorId",id);
        model.addAttribute("doctorSchedule",new DoctorSchedule());
        return "admin_doctor_schedule";
    }

    @PostMapping("/admin/doctor/add_schedule/{doctorId}")
    public String adminDoctorAddSchedule(@PathVariable("doctorId") String doctorId,DoctorSchedule doctorSchedule){
        doctorSchedule.setDoctor_id(doctorId);
        doctorDao.insertDoctorSchedule(doctorSchedule);
        return "redirect:/admin/doctor/schedule/"+doctorSchedule.getDoctor_id();
    }

    @GetMapping("/admin/doctor/delete_schedule/{doctorId}/{id}")
    public String adminDoctorDeleteSchedule(@PathVariable("id") int id,@PathVariable("doctorId") String doctorId){
        doctorDao.deleteDoctorSchedule(id);
        return "redirect:/admin/doctor/schedule/"+doctorId;
    }

    @GetMapping("/admin/doctor/profile/{id}")
    public String doctorProfile(@PathVariable("id") String id, Model model){
        Doctor doctor= doctorDao.getDoctor(id);
        model.addAttribute("doctor",doctor);
        return "doctor_profile";
    }

    @GetMapping("/admin/doctor/edit/form/{id}")
    public String adminServiceTestEditForm(@PathVariable("id") String id,Model model){
        Doctor doctor= doctorDao.getDoctor(id);
        model.addAttribute("doctor",doctor);
        return "admin_doctor_edit_form";
    }

    @PostMapping("/admin/doctor/edit/{id}")
    public String adminServiceTestEdit(@PathVariable("id") String id,Doctor doctor){
        doctorDao.updateDoctor(id,doctor);
        return "redirect:/admin/user/doctor";
    }

}
