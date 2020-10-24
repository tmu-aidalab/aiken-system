package com.aiken.bibpaper.service.login;

import java.util.Collection;
import java.util.Optional;

import com.aiken.bibpaper.domain.login.DbUserDetails;
import com.aiken.bibpaper.domain.login.LoginUser;
import com.aiken.bibpaper.domain.login.UserAccount;
import com.aiken.bibpaper.mapper.login.LoginMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DbUserDetailsService implements UserDetailsService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
        // DBからユーザ情報を取得。
        UserAccount account = Optional.ofNullable(loginMapper.findUserAccount(mailAddress))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        // ログイン回数及び最終ログイン日時の更新。
        LoginUser count = new LoginUser();
        count.setId(loginMapper.loginUserId(mailAddress));
        count.setLoginCount(loginMapper.userLoginCount(loginMapper.loginUserId(mailAddress)) + 1);

        loginMapper.insertLoginCount(count);

        return new DbUserDetails(account, getAuthorities(account));
    }

    private Collection<GrantedAuthority> getAuthorities(UserAccount account) {
        // 認可が通った時にこのユーザに与える権限の範囲を設定する。
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

}