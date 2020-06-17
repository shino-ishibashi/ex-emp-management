package jp.co.sample.controller;


import jp.co.sample.domain.Administrator;
import jp.co.sample.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    private AdministratorService adminService;


    @ModelAttribute
    public LoginForm loginFormSetUp(){
        return new LoginForm();
    }

    @RequestMapping("/login")
    public String toLoginForm() {
        return "/administrator/login";
    }

    @RequestMapping("/get-form")
    public String getLoginForm(LoginForm loginForm, Model model) {
//        DBからデータを受け取るってそれがnullだったらエラーを返す
//        あったらログイン成功としてemployee一覧を表示
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        Administrator admin = adminService.findAdminByEmailAndPassword(email,password);

        if(Objects.isNull(admin)){
            model.addAttribute("isFindAdmin",true);
            return toLoginForm();
        }else{
            return "/employee/list";
        }
    }
}
