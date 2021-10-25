package com.example.demo.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
		return "home/home";
		
	}
	
	@GetMapping("/search")
	public String search(@ModelAttribute @Validated SearchDate searchDate, BindingResult bindingResult, Model model) throws ParseException {
		
		//入力チェックに引っかかった場合、home.htmlに戻る
		if (bindingResult.hasErrors()) {
			return getHome(searchDate, model);
		}
		
		//serviceクラスのselectAllメソッドを呼び出す
		//DBの値をリスト型DateCalculationフォームが返ってくるため、List型を用意
		//戻ってきたものがList型の変数resultDateに入る
		List<DateCalculation> resultDate = service.selectAll();
		
		//Map型の変数resultCalcを生成する
		Map<Integer, Object> resultCalc = new HashMap<>();
		
		//for文で要素数文取り出し繰り返す
		for (int i = 0; i < resultDate.size(); i++) {
			//for文でresultDateの要素を取り出して、dateに代入
			DateCalculation date = resultDate.get(i);
			
			//画面側から入力された値をsearchDateから取得して、LocalDate型のresultに入れる
			LocalDate result = searchDate.getSearchDate();

			//dateに取り出した年月日に、画面から取り出してきたresultの値をそれぞれをプラスする
			result = result.plusYears(date.getYear());
			result = result.plusMonths(date.getMonth());
			result = result.plusDays(date.getDay());
			
			//プラスされた値resultを、putでresultCalcに追加。
			resultCalc.put(i+1, result);
		}
//		System.out.println(resultCalc);
		model.addAttribute("resultDate", resultDate);
		model.addAttribute("resultCalc", resultCalc);
	
		//home.htmlにリダイレクト
		return "home/home";
		
	}
	
	//register.htmlに遷移
	@GetMapping("/register")
	public String registerDisplay(@ModelAttribute DateCalculation date) {
		return "home/register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute DateCalculation date) {
		
		//serviceクラスのinsertメソッドを呼び出し、dateを引数で渡す、画面から入力された値がFormクラスに入っている
		service.insert(date);
		
		return "redirect:/home";		
	}
	
	@GetMapping("/update/{dateId}")
	public String updateDisplay(@PathVariable String dateId, Model model) {
		//serviceクラスのselectOneメソッドを呼び出す、dateIdを引数で指定
		//dateIdで指定した値のものが返ってきて、DateCalculation型の変数updateDateに入る
		DateCalculation updateDate = service.selectOne(dateId);
		
		//変数updateDateを持ってupdate.htmlへ遷移する
		model.addAttribute("updateDate", updateDate);
		//System.out.println(updateDate);
		return "home/update";
	}
	
	@PostMapping("/update/{dateId}")
	public String update(@ModelAttribute DateCalculation date) {
		//selectOneされた画面で更新する値を、dateに入れて、serviceクラスのupdateメソッドが呼ばれる
		//データが更新されてhome.htmlへ遷移する
		service.update(date);
		//System.out.println(date);
		return "redirect:/home";
	}
	//GetMappingからPostMappingへ変更
	@PostMapping("/delete/{dateId}")
	public String delete(@PathVariable String dateId) {
		//serviceクラスのdeleteメソッドを呼び出し、指定したIdのdateIdを引数にする
		service.delete(dateId);
		//指定したIdのデータが削除され、home.htmlへ遷移する
		return "redirect:/home";
	}
}