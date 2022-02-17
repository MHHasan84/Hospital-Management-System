package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminBedController {
    private BedDao bedDao=new BedDao();
    private WardDao wardDao=new WardDao();

    @GetMapping("/admin/bed")
    public String adminBed(Model model){
        List<Bed> bedList=bedDao.getAllBed();
        model.addAttribute("bedList",bedList);
        return "admin_bed";
    }

    @GetMapping("/admin/bed/add/form")
    public String adminBedAddForm(Model model){
        model.addAttribute("bed",new Bed());
        List<Ward> wardList=wardDao.getAllWard();
        model.addAttribute("wardList",wardList);
        return "admin_bed_add_form";
    }

    @PostMapping("/admin/bed/add")
    public String adminBedAdd(Bed bed){
        bedDao.insertBed(bed);
        return "redirect:/admin/bed";
    }

    @GetMapping("/admin/bed/edit/form/{bed_no}")
    public String adminBedEditForm(@PathVariable("bed_no") String bed_no, Model model){
        Bed bed=bedDao.getBed(bed_no);
        model.addAttribute("bed",bed);
        List<Ward> wardList=wardDao.getAllWard();
        model.addAttribute("wardList",wardList);
        return "admin_bed_edit_form";
    }

    @PostMapping("/admin/bed/edit/{bed_no}")
    public String adminBedEdit(@PathVariable("bed_no") String bed_no,Bed bed){
        bedDao.updateBed(bed_no,bed);
        return "redirect:/admin/bed";
    }
}
