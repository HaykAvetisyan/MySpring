package com.example.myspring.controller;

import com.example.myspring.model.Company;
import com.example.myspring.repository.CompanyRepository;
import com.example.myspring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/companies")
    public String show(ModelMap modelMap) {
        List<Company> all = companyRepository.findAll();
        modelMap.addAttribute("allComp", all);
        return "companies";
    }

    @GetMapping("/addCompany")
    public String addComp(ModelMap modelMap) {
        modelMap.addAttribute("comp", companyRepository.findAll());
        return "addcompany";
    }

    @PostMapping("/saveCompany")
    public String saveComp(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "redirect:/";
    }

    @GetMapping("/deleteCompany")
    @Transactional
    public String delete(@RequestParam("id") int id) {
        employeeRepository.deleteEmployeeByCompanyId(id);
        companyRepository.deleteById(id);

        return "redirect:/companiees";
    }

}
