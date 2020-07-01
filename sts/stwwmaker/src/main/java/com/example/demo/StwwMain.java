package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

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
	private qaListRepository qalistRepository2;
	
	@Controller
	@RequestMapping(value="/demo")
	public class Controller2
	{
		@Autowired
		private qaListRepository qalistRepository;
		@PostMapping(path="/add")
		public @ResponseBody String addNewUser(@RequestParam String name,@RequestParam String email)
		{
			qaListRow r = new qaListRow("Question1","Answer1");
			r.setQuestion("Question1Next");
			
			qalistRepository.save(r);
			return "<p>Saved</p>";
		}
		
		@GetMapping(path="/all")
		public @ResponseBody Iterable<qaListRow> getAllUsers(){
			return qalistRepository.findAll();
		}
		
	}
	
	
	@RequestMapping(value="/stwwtest")
	public ModelAndView index(Model model) {
		ModelAndView m = new ModelAndView();
		m.setViewName("index1.html");
		return m;
	}
	
	@PostMapping(value="/qapush")
	public ModelAndView qapush(Model model,@RequestParam String question,@RequestParam String answer) {
		ModelAndView m = new ModelAndView();
		ArrayList<qaListRow> qalist = new ArrayList<qaListRow>();	//HTMl表示用のリスト
		//貰ったCSVを展開
		
		//SQLにアクセスする
		qalistRepository2.save(new qaListRow(question,answer));

		//tableに表示させたい要素はqaStrsにぶっこむ(以下ぶっこみサンプル)
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

