package com.example.myspring.controller;

import com.example.myspring.model.Company;
import com.example.myspring.model.Employee;
import com.example.myspring.repository.CompanyRepository;
import com.example.myspring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository companyRepository;
    String upload = "C:\\Users\\Edgar\\IdeaProjects\\MySpring\\src\\main\\resources\\static\\upload\\";

    @GetMapping("/employees")
    public String show(ModelMap modelMap) {
        List<Employee> all = employeeRepository.findAll();
        modelMap.addAttribute("all", all);
        return "employees";
    }

    @GetMapping("/addEmployee")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("comp", companyRepository.findAll());
        return "addemployee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee, @RequestParam("image") MultipartFile file) throws IOException {

        String profilePic = System.nanoTime() + "_" + file.getOriginalFilename();
        File image = new File(upload, profilePic);
        file.transferTo(image);
        int compId = employee.getCompany().getId();
        Optional<Company> byId = companyRepository.findById(compId);
        int newSize = byId.get().getSize() + 1;
        byId.get().setSize(newSize);
        employee.setPictureUrl(profilePic);
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @GetMapping("/deleteEmployee")
    public String delete(@RequestParam("id") int id) {
        Employee employee = employeeRepository.getById(id);
        int compId = employee.getCompany().getId();
        Optional<Company> byId = companyRepository.findById(compId);
        int newSize = byId.get().getSize() - 1;
        byId.get().setSize(newSize);
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }

}
