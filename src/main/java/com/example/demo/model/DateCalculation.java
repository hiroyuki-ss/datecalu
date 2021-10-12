package com.example.demo.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DateCalculation {
	
//	private int id;
	
	@NotBlank
	private String dateId;
	
	@NotBlank
	private String dateName;
	
	@NotBlank
	private int year;
	
	@NotBlank
	private int month;
	
	@NotBlank
	private int day;
}
