package com.aiken.bibpaper.controller.login;

import com.aiken.bibpaper.domain.login.RegisterUser;
import com.aiken.bibpaper.domain.login.RegisterUserForm;
import com.aiken.bibpaper.service.login.RegisterUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RegisterUserController {

    @Autowired
    private RegisterUserService registerUserService;

    /**
     * 会員情報入力画面に遷移する。
     */
    @GetMapping("RegistrationForm")
    public String showRegisterUserForm(Model model) {

        model.addAttribute(new RegisterUserForm());

        // 会員情報入力画面。
        return "RegistrationForm";
    }

    @PostMapping("Register")
    public String registerUserAccount(@ModelAttribute RegisterUserForm registerUserForm) {

        // USERテーブルにinsertする時の引数。
        RegisterUser entity = new RegisterUser();

        entity.setUserName(registerUserForm.getUserName());
        entity.setEmail(registerUserForm.getEmail());
        entity.setPassword(registerUserForm.getPassword());

        // USERテーブルにinsertする。
        registerUserService.registerUser(entity);

        return "Result";
    }
}