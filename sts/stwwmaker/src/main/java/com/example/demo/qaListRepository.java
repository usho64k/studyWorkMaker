package com.example.demo;

//DBに触るためにinterfaceを用意するが、そのリポジトリの継承元インターフェースと
//qaListを宣言するためのリスト
import java.util.List;
import org.springframework.data.repository.CrudRepository;

//qaListRowを使ってDBにアクセスするInterface
public interface qaListRepository extends CrudRepository<qaListRow, Long>{
	List<qaListRow> findByQuestion(String q);
	List<qaListRow> findByAnswer(String str);
	
}