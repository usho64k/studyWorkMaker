package com.example.demo;

import org.springframework.stereotype.Controller;					//WebController呼び出し(Jastsなら必須)
import org.springframework.web.bind.annotation.RequestMapping;		//HTMLアクセスを受け取るRequestMappingクラス

import org.springframework.web.bind.annotation.RequestParam;		//HTMLのRequestParameterを受け取るクラス
import org.springframework.web.servlet.ModelAndView;				//ModelAndViewオブジェクト

import org.springframework.web.bind.annotation.ModelAttribute;		//HTML間でデータをJavaClassに固めて投げるクラス

@Controller
public class mainCtrller {
	@RequestMapping(value="/stwwweb")
	private String stwwweb() {
		return "/index.html";
	}
	
	@RequestMapping(value="/index1")
	private String index() {
		return "index1";	//index1.htmlを返す
	}
	
	@RequestMapping(value="/index3")
	private String index3() {
		return "index3";	//index3.htmlを返す
	}
	
	@RequestMapping(value="/callResponse")
	public ModelAndView response(@RequestParam("name") String name, @RequestParam("tweet") String tweet) {
		//RequestParamはURLで受け渡された変数。CGI(Perl)でいうところのスーパーグローバル変数$_GET['name']にあたる
		
		ModelAndView mav = new ModelAndView();	//このオブジェクトが何かはあんまりよくわかってない
		mav.setViewName("index1");				//index1を貰ってくる
		mav.addObject("helloResponse", "Hello, I am " + name + ". Nice to meet you.");
		mav.addObject("tweetResponse", tweet);
		
		return mav;								//index1のhelloResponseとtweetResponseのTextをいじって返す
	}
	
	@RequestMapping(value="/tellResponse")
	public ModelAndView response2(@ModelAttribute ParamObject_test1 param) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index1");
		mav.addObject("response",param);
		return mav;
	}
	
	
	
	//tellResponseアクセスで返すオブジェクト
	public static class ParamObject_test1{
		private String name;
		private String tweet;
		
		public String getName() {return name;}
		public void setName(String str) {this.name = str;}
		
		public String getTweet() {return tweet;}
		public void setTweet(String str) {this.tweet = str;}
	}
}
