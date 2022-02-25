package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.List;

@Controller
public class AdminUserTechnicianController {
    TechnicianDao technicianDao=new TechnicianDao();

    @GetMapping("/admin/user/technician")
    public String adminUserTechnician(Model model){
        List<Technician> technicianList=technicianDao.getAllTechnician();
        model.addAttribute("technician_list",technicianList);
        model.addAttribute("user",new User());
        return "admin_user_technician";
    }

    @PostMapping("/admin/user/technician/search")
    public String adminUserTechnicianBySearch(User user,Model model){
        List<Technician> technicianList=technicianDao.getAllTechnicianById(user.getId());
        model.addAttribute("technician_list",technicianList);
        return "admin_user_technician";
    }

    @GetMapping("/admin/add_technician_form")
    public String adminAddTechnicianForm(Model model){
        model.addAttribute("technician",new Technician());
        return "admin_user_technician_addnew";
    }

    @PostMapping("/admin/add_technician")
    public String adminAddTechnician(Technician technician) throws ParseException {
        technicianDao.insertTechnician(technician);
        return "redirect:/admin/user/technician";
    }

    @GetMapping("/admin/technician/schedule/{id}")
    public String adminTechnicianSchedule(@PathVariable("id") String id, Model model){
        List<EmployeeSchedule> employeeScheduleList=technicianDao.getAllSchedule(id);
        model.addAttribute("employeeScheduleList",employeeScheduleList);
        model.addAttribute("technicianId",id);
        model.addAttribute("employeeSchedule",new EmployeeSchedule());
        return "admin_technician_schedule";
    }

    @PostMapping("/admin/technician/add_schedule/{technicianId}")
    public String adminTechnicianAddSchedule(@PathVariable("technicianId") String technicianId,EmployeeSchedule employeeSchedule){
        employeeSchedule.setEmployee_id(technicianId);
        technicianDao.insertEmployeeSchedule(employeeSchedule);
        return "redirect:/admin/technician/schedule/"+employeeSchedule.getEmployee_id();
    }

    @GetMapping("/admin/technician/delete_schedule/{technicianId}/{id}")
    public String adminTechnicianDeleteSchedule(@PathVariable("id") int id,@PathVariable("technicianId") String technicianId){
        technicianDao.deleteEmployeeSchedule(id);
        return "redirect:/admin/technician/schedule/"+technicianId;
    }

    @GetMapping("/admin/technician/profile/{id}")
    public String technicianProfile(@PathVariable("id") String id, Model model){
        Technician technician= technicianDao.getTechnician(id);
        model.addAttribute("technician",technician);
        return "admin_technician_profile";
    }

    @GetMapping("/admin/technician/edit/form/{id}")
    public String adminTechnicianEditForm(@PathVariable("id") String id,Model model){
        Technician technician= technicianDao.getTechnician(id);
        model.addAttribute("technician",technician);
        return "admin_technician_edit_form";
    }

    @PostMapping("/admin/technician/edit/{id}")
    public String adminTechnicianEdit(@PathVariable("id") String id,Technician technician){
        technicianDao.updateTechnician(id,technician);
        return "redirect:/admin/user/technician";
    }

}
