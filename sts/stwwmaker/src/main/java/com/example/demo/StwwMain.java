package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//Springframework.web.bind系 汎用クラス
import org.springframework.ui.Model;							//引数
import org.springframework.web.bind.annotation.RequestMapping;	//HTMLアクセス関数登録するためのアノテーション
import org.springframework.web.bind.annotation.RequestParam;	//URL引数をうけとるためのアノテーション
import org.springframework.web.bind.annotation.ModelAttribute;	//HTMLに渡すThymeleaf機能クラスを使うアノテーション
import org.springframework.web.bind.annotation.ResponseBody;	//POSTで要求されるBody側Stringを返すことを明示するアノテーション
import org.springframework.web.bind.annotation.PostMapping;		//HTTPリクエストのPOSTアクセスアノテーション
import org.springframework.web.bind.annotation.GetMapping;		//HTTPリクエストのGETアクセスアノテーション
import org.springframework.web.multipart.MultipartFile;			//CSV処理に必要
import org.springframework.web.servlet.mvc.support.RedirectAttributes;	//使っていない？
import org.springframework.web.servlet.ModelAndView;			//HTMLViewそのもの
import org.springframework.beans.factory.annotation.Autowired;	//なにこれ？

//配列処理用
import java.util.ArrayList;
import java.util.List;

//CSV処理に必要なやつ
import java.io.IOException;
import java.io.InputStream;										//最初に処理するStream
import java.io.Reader;											//Reader
import java.io.InputStreamReader;								//StreamReader(初期化子)
import java.io.BufferedReader;									//StreamからBufferにする

//コンソール画面へのデバッグ用
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class StwwMain {
	//MySQLへのRepositoryInterface
	@Autowired
	private qaListRepository qalistRepository2;
	
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
	
	//メインページ
	@RequestMapping(value="/stwwtest")
	public ModelAndView index(Model model) 
	{
		ModelAndView m = new ModelAndView();
		m.setViewName("index1.html");
		m.addObject("qaListRow",new qaListRow());
		return m;
	}
	
	//問題・解答データの挿入
	@PostMapping(value="/qaPush")
	public ModelAndView qapush(@RequestParam("qaCsv") MultipartFile file,RedirectAttributes red,Model model) 
	{
		ModelAndView m = new ModelAndView();
		ArrayList<qaListRow> qalist = new ArrayList<qaListRow>();	//HTMl表示用のリスト
		qalist.add(new qaListRow("Question","Answer"));
		//貰ったCSVを展開
		red.addFlashAttribute("message","You successfully uploaded "+file.getOriginalFilename()+"!");

		if(file.isEmpty() || file == null) 
		{
			//空文字、もしくはNullだった場合
			final Logger log = 	LoggerFactory.getLogger(StwwmakerApplication.class);
			log.info("brank character");
		}
		else
		{
			//ログ表示(デバッグ用コンソール画面)
			final Logger log = 	LoggerFactory.getLogger(StwwmakerApplication.class);
			log.info(file.getOriginalFilename());
			
			//CSV展開処理
			List<String> lines = new ArrayList<String>();
			String line = null;
			try 
			{
				//ファイル内を確認
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
				//読み込みできなかった場合
				log.info("Can't read contents.");
			}
			//読み込みできた部分まで出力
			for(String s : lines)
			{
				//"="で前後に分ける
				String[] elem = s.split("=",2);
				//SQLにアクセスする
				qalistRepository2.save(new qaListRow(elem[0],elem[1]));
				//ログ出力(ページに表示)
				qalist.add(new qaListRow(elem[0],elem[1]));
			}
		}

		//qaStrsに反映
		model.addAttribute("qaStrs",qalist);
		
		//転送結果を表示
		m.setViewName("index_qapush.html");
		return m;
	}
	
	@RequestMapping(value="/qanda")
	public ModelAndView qanda(@ModelAttribute qaObject qaList,@RequestParam("succ") int succ,@RequestParam("fail") int fail,Model model) {
		ModelAndView m = new ModelAndView();
		model.addAttribute("succ",succ);				//正解数
		model.addAttribute("fail",fail);				//不正解数
		model.addAttribute("quest_num",succ+fail+1);	//問題番号
		//問題
		model.addAttribute("quest_str","きんにくをローマ字で書くとどれ？");
		
		//選択肢4つ
		List<BranchesStrrr> listBranch= new ArrayList<BranchesStrrr>();
		listBranch.add(new BranchesStrrr(true,"kinniku"));
		listBranch.add(new BranchesStrrr(false,"mattyo"));
		listBranch.add(new BranchesStrrr(false,"筋肉"));
		listBranch.add(new BranchesStrrr(false,"あほか"));
		model.addAttribute("branches",listBranch);
		
		
		m.setViewName("index_qa.html");
		
		return m;
	}
	

	public static class qaObject{
		private String filename;
		
		public String getFilename() { return filename;	}
		public void setFilename(String str) {	filename = str; }
	}	
}
