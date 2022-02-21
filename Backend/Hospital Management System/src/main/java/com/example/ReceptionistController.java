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
public class ReceptionistController {
    ReceptionistDao receptionistDao=new ReceptionistDao();
    DoctorDao doctorDao=new DoctorDao();
    TestDao testDao=new TestDao();
    OperationDao operationDao=new OperationDao();
    WardDao wardDao=new WardDao();
    BedDao bedDao=new BedDao();

    @GetMapping("/receptionist/{receptionist_id}/profile")
    public String receptionistProfile(@PathVariable("receptionist_id") String receptionistId, Model model){
        Receptionist receptionist= receptionistDao.getReceptionist(receptionistId);
        model.addAttribute("receptionistId",receptionistId);
        model.addAttribute("receptionist",receptionist);
        return "receptionist_profile";
    }

    @GetMapping("/receptionist/{receptionist_id}/all_appointment")
    public String receptionistAllAppointment(@PathVariable("receptionist_id") String receptionistId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        List<Appointment> appointmentList=receptionistDao.getAllAppointment();
        model.addAttribute("appointmentList",appointmentList);
        return "receptionist_all_appointments";
    }

    @GetMapping("/receptionist/{receptionist_id}/all_due_appointment")
    public String receptionistAllDueAppointment(@PathVariable("receptionist_id") String receptionistId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        List<Appointment> appointmentList=receptionistDao.getAllAppointment();
        model.addAttribute("appointmentList",appointmentList);
        return "receptionist_all_due_appointments";
    }

    @GetMapping("/receptionist/{receptionist_id}/approve_appointment/{appointment_id}")
    public String receptionistApproveAppointment(@PathVariable("receptionist_id") String receptionistId,@PathVariable("appointment_id") int appointmentId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        receptionistDao.approveAppointment(appointmentId);
        return "redirect:/receptionist/"+receptionistId+"/all_due_appointment";
    }

    @GetMapping("/receptionist/{receptionist_id}/delete_appointment/{appointment_id}")
    public String receptionistDeleteAppointment(@PathVariable("receptionist_id") String receptionistId,@PathVariable("appointment_id") int appointmentId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        receptionistDao.deleteAppointment(appointmentId);
        return "redirect:/receptionist/"+receptionistId+"/all_due_appointment";
    }

    @GetMapping("/receptionist/{receptionist_id}/approve_test_form")
    public String receptionistApproveTestForm(@PathVariable("receptionist_id") String receptionistId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        model.addAttribute("patientTest",new PatientTest());
        List<Doctor> doctorList=doctorDao.getAllDoctor();
        model.addAttribute("doctorList",doctorList);
        List<Test> testList=testDao.getAllTest();
        model.addAttribute("testList",testList);
        return "receptionist_approve_test_form";
    }

    @PostMapping("/receptionist/{receptionist_id}/approve_test")
    public String receptionistApproveTest(@PathVariable("receptionist_id") String receptionistId,PatientTest patientTest){
        receptionistDao.insertPatientTest(patientTest);
        return "redirect:/receptionist/"+receptionistId+"/approve_test_form";
    }

    @GetMapping("/receptionist/{receptionist_id}/approve_operation_form")
    public String receptionistApproveOperationForm(@PathVariable("receptionist_id") String receptionistId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        model.addAttribute("patientOperation",new PatientOperation());
        List<Doctor> doctorList=doctorDao.getAllDoctor();
        model.addAttribute("doctorList",doctorList);
        List<Operation> operationList=operationDao.getAllOperation();
        model.addAttribute("operationList",operationList);
        return "receptionist_approve_operation_form";
    }

    @PostMapping("/receptionist/{receptionist_id}/approve_operation")
    public String receptionistApproveOperation(@PathVariable("receptionist_id") String receptionistId,PatientOperation patientOperation){
        receptionistDao.insertPatientOperation(patientOperation);
        return "redirect:/receptionist/"+receptionistId+"/approve_operation_form";
    }

    @GetMapping("/receptionist/{receptionist_id}/ward_list")
    public String receptionistWardList(@PathVariable("receptionist_id") String receptionistId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        List<Ward> wardList=wardDao.getAllWard();
        model.addAttribute("wardList",wardList);
        return "receptionist_ward_list";
    }

    @GetMapping("/receptionist/{receptionist_id}/admit_patient_form/{ward_no}")
    public String receptionistAdmitPatientForm(@PathVariable("receptionist_id") String receptionistId,@PathVariable("ward_no") String wardNo, Model model){
        model.addAttribute("receptionistId",receptionistId);
        List<Bed> bedList=bedDao.getAllBedByWardNo(wardNo);
        model.addAttribute("bedList",bedList);
        model.addAttribute("admittedPatient",new AdmittedPatient());
        List<Doctor> doctorList=doctorDao.getAllDoctor();
        model.addAttribute("doctorList",doctorList);
        model.addAttribute("wardNo",wardNo);
        return "receptionist_admit_patient_form";
    }

    @PostMapping("/receptionist/{receptionist_id}/admit_patient/{ward_no}")
    public String receptionistAdmitPatient(@PathVariable("receptionist_id") String receptionistId,@PathVariable("ward_no") String wardNo,AdmittedPatient admittedPatient){
        admittedPatient.setWard_no(wardNo);
        receptionistDao.insertAdmittedPatient(admittedPatient);
        return "redirect:/receptionist/"+receptionistId+"/ward_list";
    }


    @GetMapping("/receptionist/{receptionist_id}/all_operation_schedule")
    public String receptionistAllOperationSchedule(@PathVariable("receptionist_id") String receptionistId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        List<PatientOperation> patientOperationList=receptionistDao.getAllPatientOperation();
        model.addAttribute("patientOperationList",patientOperationList);
        return "receptionist_all_operation_schedule";
    }

    @GetMapping("/receptionist/{receptionist_id}/all_previous_admitted_patient")
    public String receptionistAllPreviousAdmittedPatient(@PathVariable("receptionist_id") String receptionistId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        List<AdmittedPatient> admittedPatientList=receptionistDao.getAllAdmittedPatient();
        model.addAttribute("admittedPatientList",admittedPatientList);
        return "receptionist_all_previous_admitted_patient";
    }

    @GetMapping("/receptionist/{receptionist_id}/all_present_admitted_patient")
    public String receptionistAllPresentAdmittedPatient(@PathVariable("receptionist_id") String receptionistId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        List<AdmittedPatient> admittedPatientList=receptionistDao.getAllAdmittedPatient();
        model.addAttribute("admittedPatientList",admittedPatientList);
        return "receptionist_all_present_admitted_patient";
    }

    @GetMapping("/receptionist/{receptionist_id}/release_patient/{admitted_id}")
    public String receptionistReleasePatient(@PathVariable("receptionist_id") String receptionistId,@PathVariable("admitted_id") int admittedId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDate= dtf.format(now);

        receptionistDao.setReleaseDate(admittedId,currentDate);
        return "redirect:/receptionist/{receptionist_id}/all_present_admitted_patient";
    }

    @GetMapping("/receptionist/{receptionist_id}/all_bill")
    public String receptionistBill(@PathVariable("receptionist_id") String receptionistId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        List<Bill> billList=receptionistDao.getAllBill();
        model.addAttribute("billList",billList);
        return "receptionist_all_bill";
    }

    @GetMapping("/receptionist/{receptionist_id}/clear_bill/{bill_id}")
    public String receptionistBillClear(@PathVariable("receptionist_id") String receptionistId,@PathVariable("bill_id") int billId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        receptionistDao.clearBill(billId);
        return "redirect:/receptionist/{receptionist_id}/all_bill";
    }

    @GetMapping("/receptionist/{receptionist_id}/schedule")
    public String receptionistSchedule(@PathVariable("receptionist_id") String receptionistId, Model model){
        model.addAttribute("receptionistId",receptionistId);
        List<EmployeeSchedule> employeeScheduleList=receptionistDao.getAllSchedule(receptionistId);
        model.addAttribute("employeeScheduleList",employeeScheduleList);
        return "receptionist_schedule";
    }

}
