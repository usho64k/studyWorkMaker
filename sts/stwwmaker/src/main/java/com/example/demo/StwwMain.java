package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//Springframework.web.bind系 汎用クラス
import org.springframework.ui.Model;							//引数
import org.springframework.web.bind.annotation.RequestMapping;	//HTMLアクセス関数登録
import org.springframework.web.bind.annotation.RequestParam;	//URL引数をうけとるやつ
import org.springframework.web.bind.annotation.ModelAttribute;	//HTMLに渡すThymeleaf機能クラス
import org.springframework.web.bind.annotation.ResponseBody;	//これはしらない
import org.springframework.web.bind.annotation.PostMapping;		//HTTPリクエストのPOSTアクセス？
import org.springframework.web.bind.annotation.GetMapping;		//HTTPリクエストのGETアクセス？
import org.springframework.web.bind.annotation.ExceptionHandler;	//例外ハンドラ
import org.springframework.web.bind.annotation.PathVariable;	//なんだろう？
import org.springframework.web.multipart.MultipartFile;			//CSV処理に必要
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import org.springframework.web.servlet.ModelAndView;			//HTMLViewそのもの
import org.springframework.beans.factory.annotation.Autowired;	//なにこれ？

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;					//POSTの中身受け取るやつ

//CSV処理に必要なやつ
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class StwwMain {
	//MySQLへのRepositoryInterface
	@Autowired
	private qaListRepository qalistRepository2;
	//CSVへのService Interface
	//private final StorageService storageService;
	
	//@Autowired
	//public StwwMain(StorageService storageService) {
	//	this.storageService = storageService;
	//}
	
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
		m.addObject("qaListRow",new qaListRow());
		return m;
	}
	
	@PostMapping(value="/qaPush")
	public ModelAndView qapush(@RequestParam("qaCsv") MultipartFile file,RedirectAttributes red,Model model) {
		ModelAndView m = new ModelAndView();
		ArrayList<qaListRow> qalist = new ArrayList<qaListRow>();	//HTMl表示用のリスト
		//貰ったCSVを展開
		red.addFlashAttribute("message","You successfully uploaded "+file.getOriginalFilename()+"!");

		if(file.isEmpty()) {
			final Logger log = 	LoggerFactory.getLogger(StwwmakerApplication.class);
			log.info("brank character");
		}
		else
		{
			final Logger log = 	LoggerFactory.getLogger(StwwmakerApplication.class);
			log.info(file.toString());
			
			//CSV展開処理
			List<String> lines = new ArrayList<String>();
			String line = null;
			try {
				InputStream stream = file.getInputStream();
				Reader reader = new InputStreamReader(stream);
				BufferedReader buf = new BufferedReader(reader);
				while((line = buf.readLine()) != null)
				{
					lines.add(line);
					log.info(line);
				}	
			}
			catch(IOException e) 
			{
				log.info("Can't read contents.");
			}
			for(String s : lines){
				String[] elem = s.split("=",2);
				qalistRepository2.save(new qaListRow(elem[0],elem[1]));
				qalist.add(new qaListRow(elem[0],elem[1]));
			}
			
			
		}
		//SQLにアクセスする
		qalistRepository2.save(new qaListRow("TaQ","ans2"));

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

