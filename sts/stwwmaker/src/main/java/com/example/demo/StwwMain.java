package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class StwwMain {
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
	
	//qapush時にHTMLに渡すときに使うquestion/answer表
	public static class qaListRow{
		private String question;
		private String answer;
		
		public String getQuestion() { return question;	}
		public void setQuestion(String str) {	question = str;	}
		public String getAnswer() {	return answer;	}
		public void setAnswer(String str) {	answer = str;	}
		
		public qaListRow(String q, String a) {
			question = q;
			answer = a;
		}
		
	}
}

