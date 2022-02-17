package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {
    DoctorDao doctorDao=new DoctorDao();
    @GetMapping("/patient/doctor_list")
    public String doctorList(Model model){
        List<Doctor> doctorList=doctorDao.getAllDoctor();
        model.addAttribute("doctorList",doctorList);
        return "patient_doctor_list";
    }
    @GetMapping("/patient/doctor_schedule/{doctor_id}")
    public String doctorSchedule(@PathVariable("doctor_id") String doctorId,Model model){
        List<DoctorSchedule> doctorScheduleList=doctorDao.getAllSchedule(doctorId);
        model.addAttribute("doctorScheduleList",doctorScheduleList);
        return "patient_doctor_schedule";
    }
    @GetMapping("/patient/add_appointment/{doctor_id}/{schedule_id}")
    public String addAppointment(@PathVariable("doctor_id") String doctorId,@PathVariable("schedule_id") int scheduleId){

    }
}
