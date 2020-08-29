package com.aiken.bibpaper.domain.login;

//ユーザのログイン回数を格納
public class LoginUser {

    private Long id;

    private Long loginCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }
}