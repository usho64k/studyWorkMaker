package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class stwwMain {
	//テスト用(一応サービスで定義しておいてもいいはず)
	@Service
	@RequestMapping(value="/demo")
	public class Controller2
	{
		//テスト用
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
}
