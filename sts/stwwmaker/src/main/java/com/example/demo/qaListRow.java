package com.example.demo;

//Entityは絶対必要らしい(DBに触らないならいらない)
import javax.persistence.Entity;


//qapush時にHTMLに渡すときに使うquestion/answer表
@Entity
public class qaListRow {

	private String question;
	private String answer;
	
	public String getQuestion() { return question;	}
	public void setQuestion(String str) {	question = str;	}
	public String getAnswer() {	return answer;	}
	public void setAnswer(String str) {	answer = str;	}
	
	protected qaListRow() {}
	public qaListRow(String q, String a) {
		question = q;
		answer = a;
	}
	
}
