package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {
	
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String userId;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 4, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "\\w*", groups = ValidGroup3.class)
	private String password;
}
