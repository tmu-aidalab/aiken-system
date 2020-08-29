package com.aiken.bibpaper.mapper.login;

import com.aiken.bibpaper.domain.login.LoginUser;
import com.aiken.bibpaper.domain.login.UserAccount;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    // USERテーブルからユーザ名とパスワードを取得。
    public UserAccount findUserAccount(String name);

    Long loginUserId(String name);

    Long userLoginCount(Long id);

    void insertLoginCount(LoginUser count);
}