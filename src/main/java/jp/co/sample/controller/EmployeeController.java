package jp.co.sample.controller;


import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute
    public UpdateEmployeeForm setEmployeeForm(){
        return new UpdateEmployeeForm();
    }

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

        UpdateEmployeeForm form = new UpdateEmployeeForm();
        form.setId(employee.getId());
        form.setName(employee.getName());
        form.setImage(employee.getImage());
        form.setGender(employee.getGender());
        form.setHireYear(employee.getHireDate().getYear());
        form.setHireMonth(employee.getHireDate().getMonthValue());
        form.setHireDay(employee.getHireDate().getDayOfMonth());
        form.setMailAddress(employee.getMailAddress());
        form.setZipCode(employee.getZipCode());
        form.setAddress(employee.getAddress());
        form.setTelephone(employee.getTelephone());
        form.setSalary(employee.getSalary());
        form.setCharacteristics(employee.getCharacteristics());
        form.setDependentsCount(employee.getDependentsCount());


        model.addAttribute("updateEmployeeForm",form);
        return "employee/detail";
    }

    @RequestMapping("/detail/update/{id}")
    public String updateEmployeeDetail(@PathVariable("id") Integer id
            ,UpdateEmployeeForm form
            ,Model model){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(form.getName());
        employee.setImage(form.getImage());
        employee.setGender(form.getGender());
        employee.setHireDate(LocalDate.of(form.getHireYear(), form.getHireMonth(), form.getHireDay()));
        employee.setMailAddress(form.getMailAddress());
        employee.setZipCode(form.getZipCode());
        employee.setAddress(form.getAddress());
        employee.setSalary(form.getSalary());
        employee.setTelephone(form.getTelephone());
        employee.setCharacteristics(form.getCharacteristics());
        employee.setDependentsCount(form.getDependentsCount());

        employeeService.update(employee);

        return "redirect:/emp/list";
    }
}
