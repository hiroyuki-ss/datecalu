package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DateCalculation;
import com.example.demo.repository.DateCalcMapper;

@Service
public class DateCalcService implements DateCalcMapper {
	

	@Autowired
	private DateCalcMapper mapper;

	@Override
	public List<DateCalculation> selectAll() {
		return mapper.selectAll();
	}
	
	@Override
	public DateCalculation selectOne(String dateId) {
		return mapper.selectOne(dateId);
	}
}