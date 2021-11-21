package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.DateCalculation;

@Mapper
public interface DateCalcMapper {
	
	
	public List<DateCalculation> selectAll();

	public DateCalculation selectOne(String dateId);
	
	public void insert(DateCalculation date);
	
	public void update(DateCalculation date);
	
	public void delete(String dateId);
	
}