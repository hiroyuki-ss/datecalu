package com.example.demo.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.DateCalculation;
import com.example.demo.model.SearchDate;
import com.example.demo.service.DateCalcService;

@Controller
public class DateCalcController {

	@Autowired
	private DateCalcService service;

	//home.htmlに遷移
	@GetMapping("/home")
	public String getHome(@ModelAttribute SearchDate searchDate, Model model) {
		return "home";
		
	}
	
	@GetMapping("/search")
	public String search(@ModelAttribute @Validated SearchDate searchDate,  BindingResult bindingResult, Model model) throws ParseException {
		

		//入力チェックに引っかかった場合、home.htmlに戻る
		if (bindingResult.hasErrors()) {
			return getHome(searchDate, model);
		}
		
		List<DateCalculation> resultDate = service.selectAll();
		
		Map<Integer, Object> resultCalc = new LinkedHashMap<>();
		
		for (int i = 0; i < resultDate.size(); i++) {
			DateCalculation date = resultDate.get(i);
			LocalDate result = searchDate.getSearchDate();
			result = result.plusYears(date.getYear());
			result = result.plusMonths(date.getMonth());
			result = result.plusDays(date.getDay());
			
			resultCalc.put(i, result);
		}
		
		model.addAttribute("resultDate", resultDate);
		model.addAttribute("resultCalc", resultCalc);
	
		//home.htmlにリダイレクト
		return "home";
		
	}
}