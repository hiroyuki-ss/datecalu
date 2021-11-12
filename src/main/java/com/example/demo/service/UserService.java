package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.MUser;
import com.example.demo.repository.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	//ユーザー登録
	public void signup(MUser user) {
		
		//パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));		
		mapper.insertOne(user);
	}
	
	////ログインユーザー情報取得
	//public MUser getLoginUser(String userId) {
	//	return mapper.findLoginUser(userId);
	//}
}
