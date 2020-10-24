package com.aiken.bibpaper.service.login;

import com.aiken.bibpaper.domain.login.RegisterUser;
import com.aiken.bibpaper.mapper.login.RegisterUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterUserService {

    @Autowired
    RegisterUserMapper registerUserMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 会員情報をDBに登録。
     */
    public void registerUser(RegisterUser entity) {

        // パスワードをハッシュ化して、insertする時の引数にセット。
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        // 会員情報をUSERテーブルにinsert。
        registerUserMapper.insertUserInfo(entity);
    }
}