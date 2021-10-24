package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DateCalculation;
import com.example.demo.repository.DateCalcMapper;

@Service
public class DateCalcService {
	

	@Autowired
	private DateCalcMapper mapper;
	
	//全件取得、DB内の全てのデータを取ってくる
	public List<DateCalculation> selectAll() {
		//MapperクラスのselectAllメソッドが呼ばれて動く
		//returnで戻ってきたものを返す
		return mapper.selectAll();
	}
	
	
	public DateCalculation selectOne(String dateId) {
		return mapper.selectOne(dateId);
	}
	
	
	public void insert(DateCalculation date) {
		//mapperクラスのinsertメソッドを呼び出し、dateを渡す
		mapper.insert(date);
	}
	
	
	public void update(DateCalculation date) {
		//画面から入力された値の入った変数date、mapperクラスのupdateメソッド呼ばれる
		//System.out.println(date);
		mapper.update(date);
		
	}
	
	
	public void delete(String dateId) {
		//mapperクラスのdeleteメソッドが呼ばれる、指定されてIdが引数dateIdで渡す
		mapper.delete(dateId);
	}
}