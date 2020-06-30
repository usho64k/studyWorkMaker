package com.example.demo;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;							//引数
import org.springframework.web.bind.annotation.RequestMapping;	//HTMLアクセス関数登録
import org.springframework.web.bind.annotation.RequestParam;	//URL引数をうけとるやつ
import org.springframework.web.bind.annotation.ModelAttribute;	//HTMLに渡すThymeleaf機能クラス
import org.springframework.web.bind.annotation.ResponseBody;	//これはしらない
import org.springframework.web.bind.annotation.PostMapping;		//HTTPリクエストのPOSTアクセス？
import org.springframework.web.bind.annotation.GetMapping;		//HTTPリクエストのGETアクセス？

import org.springframework.web.servlet.ModelAndView;			//HTMLViewそのもの

import org.springframework.beans.factory.annotation.Autowired;	//なにこれ？

import java.util.ArrayList;

@Controller
public class StwwMain {
	
	@RequestMapping(value="/testSQL")
	public class Controller2
	{
		@Autowired
		private qaListRepository qalistRepository;
		@PostMapping(path="/demo")
		public @ResponseBody String addNewUser(@RequestParam String name,@RequestParam String email)
		{
			qaListRow r = new qaListRow("Question1","Answer1");
			r.setQuestion("Question1Next");
			
			qalistRepository.save(r);
			return "SAVED";
		}
		
		@GetMapping(path="/all")
		public @ResponseBody Iterable<qaListRow> getAllUsers(){
			return qalistRepository.findAll();
		}
		
	}
	
	
	@RequestMapping(value="/")
	public ModelAndView index(Model model) {
		ModelAndView m = new ModelAndView();
		m.setViewName("index1.html");
		return m;
	}
	
	@RequestMapping(value="/qapush")
	public ModelAndView qapush(Model model) {
		ModelAndView m = new ModelAndView();
		//貰ったCSVを展開
		
		//SQLにアクセスする
		
		//tableに表示させたい要素はqaStrsにぶっこむ(以下ぶっこみサンプル)
		ArrayList<qaListRow> qalist = new ArrayList<qaListRow>();
		qalist.add(new qaListRow("How's the weather?","It's sunny."));
		qalist.add(new qaListRow("How's whether?","I'm fine."));
		qalist.add(new qaListRow("How's the feather?","It's so tired."));
		qalist.add(new qaListRow("How's the teacher?","It's fun."));
		qalist.add(new qaListRow("How's the measure?","It's 72cm."));
		model.addAttribute("qaStrs",qalist);
		
		//転送結果を表示
		m.setViewName("index_qapush.html");
		return m;
	}
	
	@RequestMapping(value="/qanda")
	public ModelAndView qanda(@ModelAttribute qaObject qaList) {
		ModelAndView m = new ModelAndView();
		m.setViewName("index_qa.html");
		return m;
	}


	public static class qaObject{
		private String filename;
		
		public String getFilename() { return filename;	}
		public void setFilename(String str) {	filename = str; }
	}
	
}

