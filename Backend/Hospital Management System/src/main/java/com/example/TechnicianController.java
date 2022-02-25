package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class TechnicianController {
    TechnicianDao technicianDao=new TechnicianDao();
    @GetMapping("/technician/{technician_id}/profile")
    public String technicianProfile(@PathVariable("technician_id") String technicianId, Model model){
        model.addAttribute("technicianId",technicianId);
        Technician technician=technicianDao.getTechnician(technicianId);
        model.addAttribute("technician",technician);
        return "technician_profile";
    }
    @GetMapping("/technician/{technician_id}/test")
    public String technicianTest(@PathVariable("technician_id") String technicianId, Model model){
        model.addAttribute("technicianId",technicianId);
        List<PatientTest> patientTestList=technicianDao.getAllTest(technicianId);
        model.addAttribute("patientTestList",patientTestList);
        return "technician_test";
    }
    @GetMapping("/technician/{technician_id}/test/{sample_no}/result")
    public String technicianTestResult(@PathVariable("technician_id") String technicianId,@PathVariable("sample_no") int sampleNo, Model model){
        model.addAttribute("technicianId",technicianId);
        PatientTest patientTest= technicianDao.getTest(sampleNo);
        model.addAttribute("patientTest",patientTest);
        model.addAttribute("sampleNo",sampleNo);
        return "technician_test_result";
    }
    @PostMapping("/technician/{technician_id}/make_test_result/{sample_no}")
    public String technicianTestMakeResult(@PathVariable("technician_id") String technicianId,@PathVariable("sample_no") int sampleNo, PatientTest patientTest){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDate= dtf.format(now);

        patientTest.setResult_date(currentDate);
        technicianDao.editTestResult(sampleNo,patientTest);
        return "redirect:/technician/"+technicianId+"/test";
    }
    @GetMapping("/technician/{technician_id}/schedule")
    public String technicianSchedule(@PathVariable("technician_id") String technicianId, Model model){
        model.addAttribute("technicianId",technicianId);
        List<EmployeeSchedule> employeeScheduleList=technicianDao.getAllSchedule(technicianId);
        model.addAttribute("employeeScheduleList",employeeScheduleList);
        return "technician_schedule";
    }
}
