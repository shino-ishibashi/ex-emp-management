package jp.co.sample.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class InsertAdministratorForm {
    @NotBlank(message = "氏名を入力してください")
    private String name;

    @Email(message = "メールアドレスを入力してください")
    private String email;

    @NotBlank(message = "パスワードを入力してください")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
