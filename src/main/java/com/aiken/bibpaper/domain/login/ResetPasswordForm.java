package com.aiken.bibpaper.domain.login;

//パスワード再設定フォームに入力された値の格納
public class ResetPasswordForm {

    private String email;

    private String newPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}