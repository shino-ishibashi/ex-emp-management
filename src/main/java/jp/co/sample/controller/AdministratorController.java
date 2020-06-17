package jp.co.sample.controller;


import jp.co.sample.domain.Administrator;
import jp.co.sample.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    private AdministratorService adminService;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public LoginForm loginFormSetUp(){
        return new LoginForm();
    }

    @ModelAttribute
    public InsertAdministratorForm insertFormSetup(){
        return new InsertAdministratorForm();
    }

    @RequestMapping("/login")
    public String toLoginForm() {
        return "/administrator/login";
    }

    @RequestMapping("/insert")
    public String toInserForm(){
        return "/administrator/insert";
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
            session.setAttribute("loginUser", admin);
            return toLoginForm();
        }else{
            return "/employee/list";
        }
    }

    @RequestMapping("/get-insert-form")
    public String insertAdmin(@Validated
            InsertAdministratorForm adminForm
            , BindingResult result
            , Model model) {

        //最初にvalidationCheck

        if(result.hasErrors()){
            return toInserForm();
        }

        Administrator insertAdmin = new Administrator();
        insertAdmin.setName(adminForm.getName());
        insertAdmin.setMailAddress(adminForm.getEmail());
        insertAdmin.setPassword(adminForm.getPassword());

        //emailが存在していたらtrue
        boolean isEmailExists = adminService.insertAdmin(insertAdmin);


        if(isEmailExists){
            //DBにemailが存在していたので,errorを返す。
            model.addAttribute("emailExistsError",true);
            return toInserForm();
        }else{
            //登録成功したのでlistへ
            return "/employee/list";
        }
    }
}
