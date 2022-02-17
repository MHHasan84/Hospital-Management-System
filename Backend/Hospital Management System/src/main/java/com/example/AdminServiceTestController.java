package com.example;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminServiceTestController {
    private TestDao testDao=new TestDao();

    @GetMapping("/admin/service/test")
    public String adminServiceTest(Model model){
        List<Test> testList=testDao.getAllTest();
        model.addAttribute("testList",testList);
        return "admin_service_test";
    }

    @GetMapping("/admin/service/test/add/form")
    public String adminServiceTestAddForm(Model model){
        model.addAttribute("test",new Test());
        return "admin_service_test_add_form";
    }

    @PostMapping("/admin/service/test/add")
    public String adminServiceTestAdd(Test test){
        testDao.insertTest(test);
        return "redirect:/admin/service/test";
    }

    @GetMapping("/admin/service/test/edit/form/{id}")
    public String adminServiceTestEditForm(@PathVariable("id") String testId,Model model){
        Test test= testDao.getTest(testId);
        model.addAttribute("test",test);
        return "admin_service_test_edit_form";
    }

    @PostMapping("/admin/service/test/edit/{id}")
    public String adminServiceTestEdit(@PathVariable("id") String testId,Test test){
        testDao.updateTest(testId,test);
        return "redirect:/admin/service/test";
    }
}
