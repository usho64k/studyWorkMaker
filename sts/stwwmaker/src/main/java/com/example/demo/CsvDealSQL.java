package com.example.demo;

//ファイルアップロードの型
import org.springframework.web.multipart.MultipartFile;

//返却用リスト
import java.util.List;
import java.util.ArrayList;

//CSV処理のためにファイルを開くために必要なもの
import java.io.IOException;						//なんらかのファイルアクセス系エラーを返すクラス
import java.io.InputStream;						//最初に処理するStream
import java.io.Reader;							//Reader
import java.io.InputStreamReader;				//Streamreader(初期化子)
import java.io.BufferedReader;					//StreamからBufferに変換する必要あり

//コンソール画面へのデバッグ用(何回も呼び出したくないのでそのうちDIする)
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvDealSQL {
	
	public static ArrayList<qaListRow> deal(MultipartFile file)
	{
		ArrayList<qaListRow> uloadContents = new ArrayList<qaListRow>();
		//ログ表示
		final Logger log = LoggerFactory.getLogger(StwwmakerApplication.class);

		//アップロードされたファイルが空文字/Nullだった場合
		if(file.isEmpty() || file == null)
		{
			//例外
			//エラーメッセージ
			log.info("brank character exception!");
			uloadContents.add(new qaListRow("Sorry, It doesn't work file.",
					"Please back to mainpage and rechoouse uploading files."));	
			return uloadContents;
		}
		//ファイル名ログ出力
		log.info(file.getOriginalFilename());

		//処理開始
		uloadContents.add(new qaListRow("Question","Answer"));
		
		//CSVを展開し、文字列リストにする(内部private関数)
		List<String> FileContents = readAndOpenFile(file);
		
		//文字列を取り出して処理する
		for(String s : FileContents)
		{
			//"="で前後に分ける
			String[] elem = s.split("=",2);
			//コンテンツに振り分ける
			uloadContents.add(new qaListRow(elem[1],elem[0]));
		}
		//返す
		return uloadContents;
	}
	
	//有効なCSVファイルを開く・読み込み処理を行う
	private static List<String> readAndOpenFile(MultipartFile file)
	{
		List<String> lines = new ArrayList<String>();
		final Logger log = LoggerFactory.getLogger(StwwmakerApplication.class);

		//CSVファイル読み取り処理開始
		try 
		{
			InputStream stream = file.getInputStream();
			Reader reader = new InputStreamReader(stream);
			BufferedReader buf = new BufferedReader(reader);
			String line;
			
			//1行ずつ読み込む
			while((line = buf.readLine()) != null)
			{
				//1行ずつ追加する(この時点で\n分割できている)
				lines.add(line);
				log.info(line);
			}
		}
		catch(IOException e)
		{
			//読み込めた部分までは返却する
			log.info("Can't read contents.");
			return lines;
		}
		return lines;
	}

}
