package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.DateCalculation;

@Mapper
public interface DateCalcMapper {
	
//	@Select("SELECT * FROM datecalc ORDER BY dateID ASC")
	public List<DateCalculation> selectAll();
	
//	@Select("SELECT * FROM datecalc WHERE dateId = #{dateId}")
	public DateCalculation selectOne(String dateId);
	
	
	
}
