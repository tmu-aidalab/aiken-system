package com.aiken.bibpaper.mapper.login;

import com.aiken.bibpaper.domain.login.RegisterUser;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterUserMapper {

    // 会員情報をUSERテーブルにinsertする。
    public void insertUserInfo(RegisterUser entity);
}