package jp.co.sample.controller;


import jp.co.sample.domain.Administrator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdministratorController {


    @ModelAttribute
    public LoginForm loginFormSetUp(){
        return new LoginForm();
    }

    @RequestMapping("/login")
    public String toLoginForm() {
        return "/administrator/login";
    }

    @RequestMapping("/get-form")
    public String getLoginForm(LoginForm loginForm) {
//        DBからデータを受け取るってそれがnullだったらエラーを返す
//        あったらログイン成功としてemployee一覧を表示
        return null;
    }
}
