package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserMapper;

@Service
public class UserDetailsServuce implements UserDetailsService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		//ユーザー情報取得
		//MUser loginUser = service.getLoginUser(username);
		
		User user = userMapper.findLoginUser(username);
		
		//ユーザーが存在しない場合
		if (user == null) {
			throw new UsernameNotFoundException(username + "is not found");
		}
		
		//権限List作成
		//GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
		//List<GrantedAuthority> authorities = new ArrayList<>();
		//authorities.add(authority);
		
		
		//UserDetails生成
		//UserDetails userDetails = (UserDetails) new User(loginUser.getUserId(),
		//		loginUser.getPassword(),
		//		authorities);
		
				
		return user;
				
		}
}
