package com.aiken.bibpaper.service.login;

import com.aiken.bibpaper.domain.login.ResetPassword;
import com.aiken.bibpaper.mapper.login.ResetPasswordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResetPasswordService {

    @Autowired
    ResetPasswordMapper resetPasswordMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * パスワードをアップデート
     */
    public void resetPassword(ResetPassword entity) {

        // パスワードをハッシュ化して、insertする時の引数にセット。
        entity.setNewPassword(passwordEncoder.encode(entity.getNewPassword()));

        // 会員情報をUSERテーブルにinsert。
        resetPasswordMapper.updateUserPassword(entity);
    }
}