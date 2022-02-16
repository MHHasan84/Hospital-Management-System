package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminServiceOperationController {
    private OperationDao operationDao=new OperationDao();

    @GetMapping("/admin/service/operation")
    public String adminServiceOperation(Model model){
        List<Operation> operationList=operationDao.getAllOperation();
        model.addAttribute("operationList",operationList);
        return "admin_service_operation";
    }

    @GetMapping("/admin/service/operation/add/form")
    public String adminServiceOperationAddForm(Model model){
        model.addAttribute("operation",new Operation());
        return "admin_service_operation_add_form";
    }

    @PostMapping("/admin/service/operation/add")
    public String adminServiceOperationAdd(Operation operation){
        operationDao.insertOperation(operation);
        return "redirect:/admin/service/operation";
    }

    @GetMapping("/admin/service/operation/edit/form/{id}")
    public String adminServiceOperationEditForm(@PathVariable("id") String operationId,Model model){
        Operation operation=operationDao.getOperation(operationId);
        model.addAttribute("operation",operation);
        return "admin_service_operation_edit_form";
    }

    @PostMapping("/admin/service/operation/edit/{id}")
    public String adminServiceOperationEdit(@PathVariable("id") String operationId,Operation operation){
        operationDao.updateOperation(operationId,operation);
        return "redirect:/admin/service/operation";
    }
}
