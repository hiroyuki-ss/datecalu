package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DateCalculation {
	
	//private int id;
	
	@NotBlank
	private String dateId;
	
	@NotBlank
	private String dateName;
	
	//以下@NotBlankからNotNullへ変更
	@NotNull
	private Integer year;
	
	@NotNull
	private Integer month;
	
	@NotNull
	private Integer day;

}
