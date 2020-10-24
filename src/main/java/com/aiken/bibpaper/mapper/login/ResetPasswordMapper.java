package com.aiken.bibpaper.mapper.login;

import com.aiken.bibpaper.domain.login.ResetPassword;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResetPasswordMapper {

    // 会員情報をUSERテーブルにinsertする。
    public void updateUserPassword(ResetPassword entity);
}