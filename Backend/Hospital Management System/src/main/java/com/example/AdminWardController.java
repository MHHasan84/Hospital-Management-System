package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminWardController {
    private WardDao wardDao=new WardDao();

    @GetMapping("/admin/ward")
    public String adminWard(Model model){
        List<Ward> wardList=wardDao.getAllWard();
        model.addAttribute("wardList",wardList);
        return "admin_ward";
    }

    @GetMapping("/admin/ward/add/form")
    public String adminWardAddForm(Model model){
        model.addAttribute("ward",new Ward());
        return "admin_ward_add_form";
    }

    @PostMapping("/admin/ward/add")
    public String adminWardAdd(Ward ward){
        wardDao.insertWard(ward);
        return "redirect:/admin/ward";
    }

    @GetMapping("/admin/ward/edit/form/{ward_no}")
    public String adminWardEditForm(@PathVariable("ward_no") String ward_no, Model model){
        Ward ward= wardDao.getWard(ward_no);
        model.addAttribute("ward",ward);
        return "admin_ward_edit_form";
    }

    @PostMapping("/admin/ward/edit/{ward_no}")
    public String adminWardEdit(@PathVariable("ward_no") String ward_no,Ward ward){
        wardDao.updateWard(ward_no,ward);
        return "redirect:/admin/ward";
    }
}
