package com.aiken.bibpaper.domain.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class DbUserDetails extends User {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // DBから取得したユーザ情報。
    private final UserAccount account;

    public DbUserDetails(UserAccount account, Collection<GrantedAuthority> authorities) {

        super(account.getName(), account.getPassword(), true, true, true, true, authorities);

        this.account = account;
    }

    public UserAccount getAccount() {
        return account;
    }

}