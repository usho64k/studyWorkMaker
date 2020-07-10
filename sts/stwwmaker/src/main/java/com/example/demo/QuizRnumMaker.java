package com.example.demo;

import java.util.Random;

//選択肢、問題番号などの番号データ取得
public class QuizRnumMaker 
{
	private int qnum,anum;
	private int gnum[] = new int[3];
	
	public QuizRnumMaker(int rmax,int cmax) 
	{
		Random r = new Random();
		
		qnum = r.nextInt(rmax);		//qnum確定
		anum = r.nextInt(cmax);		//選択肢正解番号
		
		//他の選択肢の内容を確定する(同じのを選ばないようにする)
		for(int i = 0; i < cmax - 1; i++)
		{
			gnum[i] = r.nextInt(rmax);
			for(int j = -1; j < i; j++)
			{
				if(j == -1)	{	if(qnum == gnum[i])		{	i--;	break;	}	}	//えらび直し
				else		{	if(gnum[j] == gnum[i])	{	i--;	break;	}	}	//えらび直し
			}
		}
	}
	
	public int getQnum() 	{	return qnum;	}
	public int getAnum()	{	return anum;	}
	public int[] getGnum()	{	return gnum;	}
}
