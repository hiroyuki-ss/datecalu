package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.MUser;
import com.example.demo.model.User;

@Mapper
public interface UserMapper {
	
	//ユーザー登録
	public int insertOne(MUser user);
	
	//ログインユーザー取得
	public User findLoginUser(String userId);
}