package com.example.demo.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchDate {
	
	@NotNull
	@DateTimeFormat(pattern = "yyyyMMdd")
	private LocalDate searchDate;
	


}
