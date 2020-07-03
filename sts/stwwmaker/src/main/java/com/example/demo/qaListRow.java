package com.example.demo;

//Entityは絶対必要らしい(DBに触らないならいらない)
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//qapush時にHTMLに渡すときに使うquestion/answer表
@Entity
public class qaListRow {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String question;
	private String answer;
	
	public Long getId() {return id;}
	public void setId(Long num) { id = num; }
	public String getQuestion() { return question;	}
	public void setQuestion(String str) {	question = str;	}
	public String getAnswer() {	return answer;	}
	public void setAnswer(String str) {	answer = str;	}
	
	@Override
	public String toString() {
		return String.format( "qaList[Question='%s', Answer=%s]", question,answer);
	}
	
	protected qaListRow() {}
	public qaListRow(String q, String a) {
		question = q;
		answer = a;
	}
	
}
