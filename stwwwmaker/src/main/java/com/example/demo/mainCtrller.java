package com.example.demo;

import java.util.ArrayList;											//リスト配列
import java.util.List;												//リスト

import org.springframework.stereotype.Controller;					//WebController呼び出し(Jastsなら必須)
import org.springframework.web.bind.annotation.RequestMapping;		//HTMLアクセスを受け取るRequestMappingクラス

import org.springframework.ui.Model;								//HTMLのUIの呼び込み

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
	private String index3(Model model) {
		//最終的にindex3を参照するが、予約的にparamObject_test3を設定しておく(index3.html参照)
		ParamObject_test2 ptemp = new ParamObject_test2();
		for(int i = 0; i < 10; i++)
		{
			ptemp.members.add(new Member());
		}
		
		model.addAttribute("paramObject",ptemp);
		
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
	
	@RequestMapping(value="/arytest")
	public ModelAndView response3(@ModelAttribute ParamObject_test2 members) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index3");
		mav.addObject("result",members.getMembers());
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
	
	//index3で読むが、単一クラスを指定しないと受け取れない…のか？
	public static class ParamObject_test2{
		private List<Member> members = new ArrayList<>();
		
		public List<Member> getMembers() { return members; }
		public void addMember(List<Member>members) {this.members = members;}
		
	}
	
	//ParamObject_test2のリストの親
	public static class Member{
		private String name;
		private String address;
		private String telno;
		
		public String getName() { return name; }
		public void setName(String str) {this.name = str;}
		public String getAddress() { return address; }
		public void setAddress(String str) { this.address = str;}
		public String getTelno() { return telno; }
		public void setTelno(String str) { this.telno = str; }
		
		
		//Default Constructor (Newしたときに何も指定しない型を用意しないとJavaはだめなんだっけ？
		public Member() {}
		
		//Constructor
		public Member(String name,String address,String telno)
		{
			this.name = name;
			this.address = address;
			this.telno = telno;
		}
	}
}
