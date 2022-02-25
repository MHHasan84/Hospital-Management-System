package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.List;

@Controller
public class AdminUserReceptionistController {
    ReceptionistDao receptionistDao=new ReceptionistDao();

    @GetMapping("/admin/user/receptionist")
    public String adminUserReceptionist(Model model){
        List<Receptionist> receptionistList=receptionistDao.getAllReceptionist();
        model.addAttribute("receptionist_list",receptionistList);
        model.addAttribute("user",new User());
        return "admin_user_receptionist";
    }

    @PostMapping("/admin/user/receptionist/search")
    public String adminUserReceptionistBySearch(User user,Model model){
        List<Receptionist> receptionistList=receptionistDao.getAllReceptionistById(user.getId());
        model.addAttribute("receptionist_list",receptionistList);
        return "admin_user_receptionist";
    }

    @GetMapping("/admin/add_receptionist_form")
    public String adminAddReceptionistForm(Model model){
        model.addAttribute("receptionist",new Receptionist());
        return "admin_user_receptionist_addnew";
    }

    @PostMapping("/admin/add_receptionist")
    public String adminAddReceptionist(Receptionist receptionist) throws ParseException {
        receptionistDao.insertReceptionist(receptionist);
        return "redirect:/admin/user/receptionist";
    }

    @GetMapping("/admin/receptionist/schedule/{id}")
    public String adminReceptionistSchedule(@PathVariable("id") String id, Model model){
        List<EmployeeSchedule> employeeScheduleList=receptionistDao.getAllSchedule(id);
        model.addAttribute("employeeScheduleList",employeeScheduleList);
        model.addAttribute("receptionistId",id);
        model.addAttribute("employeeSchedule",new EmployeeSchedule());
        return "admin_receptionist_schedule";
    }

    @PostMapping("/admin/receptionist/add_schedule/{receptionistId}")
    public String adminReceptionistAddSchedule(@PathVariable("receptionistId") String receptionistId,EmployeeSchedule employeeSchedule){
        employeeSchedule.setEmployee_id(receptionistId);
        receptionistDao.insertEmployeeSchedule(employeeSchedule);
        return "redirect:/admin/receptionist/schedule/"+employeeSchedule.getEmployee_id();
    }

    @GetMapping("/admin/receptionist/delete_schedule/{receptionistId}/{id}")
    public String adminReceptionistDeleteSchedule(@PathVariable("id") int id,@PathVariable("receptionistId") String receptionistId){
        receptionistDao.deleteEmployeeSchedule(id);
        return "redirect:/admin/receptionist/schedule/"+receptionistId;
    }

    @GetMapping("/admin/receptionist/profile/{id}")
    public String receptionistProfile(@PathVariable("id") String id, Model model){
        Receptionist receptionist= receptionistDao.getReceptionist(id);
        model.addAttribute("receptionist",receptionist);
        return "admin_receptionist_profile";
    }

    @GetMapping("/admin/receptionist/edit/form/{id}")
    public String adminReceptionistEditForm(@PathVariable("id") String id,Model model){
        Receptionist receptionist= receptionistDao.getReceptionist(id);
        model.addAttribute("receptionist",receptionist);
        return "admin_receptionist_edit_form";
    }

    @PostMapping("/admin/receptionist/edit/{id}")
    public String adminReceptionistEdit(@PathVariable("id") String id,Receptionist receptionist){
        receptionistDao.updateReceptionist(id,receptionist);
        return "redirect:/admin/user/receptionist";
    }

}
