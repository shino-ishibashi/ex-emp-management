package jp.co.sample.controller;


import jp.co.sample.domain.Employee;
import jp.co.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/list")
    public String toEmpList(Model model){
        List<Employee> employeeList = employeeService.findAllEmployees();
        model.addAttribute("employeeList",employeeList);
        return "employee/list";
    }

    @RequestMapping("/detail/{id}")
    public String toDetail(@PathVariable ("id") Integer id,
            Model model){
        Employee employee = new Employee();
        employee = employeeService.load(id);
        model.addAttribute("employeeDetail",employee);
        return "/employee/detail";
    }

    @RequestMapping("/detail/update/{id}")
    public String updateEmployeeDetail(@PathVariable("id") Integer id,
            String dependentsCount,Model model){
        employeeService.update(id,dependentsCount);
        return toEmpList(model);
    }







}
