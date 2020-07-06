package com.example.demo;

public class BranchesStrrr {
	private boolean right;
	private String strvalue;
	
	public boolean getRight() {	return right;}
	public void setRight(boolean b) {right = b;}
	public String getStrvalue() {return strvalue;}
	public void setStrvalue(String str) {strvalue = str;}
	
	public BranchesStrrr() {}
	public BranchesStrrr(Boolean b,String str) {
		right = b;
		strvalue = str;
	}
}
