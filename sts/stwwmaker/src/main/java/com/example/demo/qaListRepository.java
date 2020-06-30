package com.example.demo;

//DBに触るためにinterfaceを用意するが、そのリポジトリの継承元インターフェースと
//qaListを宣言するためのリスト
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.qaListRow;

//qaListRowを使ってDBにアクセスするInterface
public interface qaListRepository extends CrudRepository<qaListRow, Long>{
	
	List<qaListRow> findByQuestion(String q);
	List<qaListRow> findByAnswer(String str);
	
}