package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.GroupOrder;
import com.example.demo.form.SignupForm;
import com.example.demo.model.MUser;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//*ユーザー登録画面を表示
	@GetMapping("/signup")
	public String getSignup(Model model, @ModelAttribute SignupForm form) {
			
		//*ユーザー登録画面に遷移
		return "user/signup";
	}
		
	//ユーザー登録処理
	@PostMapping("/signup")
	public String postSignup(Model model, @ModelAttribute @Validated(GroupOrder.class)
			SignupForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			//NG：ユーザー登録画面に戻る
			return getSignup(model, form);
		}
		
		MUser user = modelMapper.map(form, MUser.class);
		service.signup(user);
		
		log.info(form.toString());
		//ログインに画面にリダイレクト
		return "redirect:/login";
	}
}
