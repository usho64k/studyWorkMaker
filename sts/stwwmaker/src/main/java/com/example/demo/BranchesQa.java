package com.example.demo;

import java.util.List;
import java.util.ArrayList;

public class BranchesQa {
	
	public static List<BranchQaselectable> getBranches(int cmax,QuizRnumMaker qr,List<qaListRow> qalist)
	{
		//リストで返す
		List<BranchQaselectable> listBranches = new ArrayList<BranchQaselectable>();

		//正解選択肢と不正解選択肢の両方を準備
		int gcnt = 0;
		for(int i = 0; i < cmax; i++)
		{
			if(qr.getAnum() == i)
			{
				listBranches.add(new BranchQaselectable(true,qalist.get(qr.getQnum()).getAnswer()));
				
			}
			else 
			{
				listBranches.add(new BranchQaselectable(false,qalist.get(qr.getGnum()[gcnt]).getAnswer()));
				gcnt++;
			}
		}
		
		//リストで返す
		return listBranches;
		
	}
}
