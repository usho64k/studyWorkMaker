package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StwwMain {
	@RequestMapping(value="/")
	public ModelAndView index(Model model) {
		ModelAndView m = new ModelAndView();
		m.setViewName("index1.html");
		return m;
	}
	
	@RequestMapping(value="/qapush")
	public ModelAndView qapush(@ModelAttribute String qakjfjk) {
		ModelAndView m = new ModelAndView();
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

