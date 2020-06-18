package jp.co.sample.controller;

import jp.co.sample.domain.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @Autowired
    private EmployeeController employeeController;

    @RequestMapping(value = {"/", "/emp/list"})
    public String home(Model model) {
        return employeeController.toEmpList(model);
    }

    @RequestMapping(value = "/hello")
    public String hello(Authentication authentication, Model model) {
        // 認証情報を取得
        Administrator admin = (Administrator)authentication.getPrincipal();
        model.addAttribute("name", admin.getName());

        return "hello";
    }
}