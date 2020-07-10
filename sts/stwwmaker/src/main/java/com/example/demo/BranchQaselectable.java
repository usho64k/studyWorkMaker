package com.example.demo;

public class BranchQaselectable {
	//Thymeleaf用クラス、正解・不正解ボタンの表示と回答内容の管理
	private boolean right;
	private String strvalue;
	
	public boolean getRight() {	return right;}
	public void setRight(boolean b) {right = b;}
	public String getStrvalue() {return strvalue;}
	public void setStrvalue(String str) {strvalue = str;}
	
	public BranchQaselectable() {}
	public BranchQaselectable(Boolean b,String str) {
		right = b;
		strvalue = str;
	}
}
