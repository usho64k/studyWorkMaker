package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StwwmakerApplication {
	
	//private static final Logger log = 	LoggerFactory.getLogger(StwwmakerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StwwmakerApplication.class, args);
	}
	/*
	@Bean
	public CommandLineRunner demo(qaListRepository repository) {
		return (args) ->{
			//いくつかリストに入れてみる
			repository.save(new qaListRow("How's the weather?","It's sunny."));
			repository.save(new qaListRow("How's whether?","I'm fine."));
			repository.save(new qaListRow("How's the feather?","It's so tired."));
			repository.save(new qaListRow("How's the teacher?","It's fun."));
			repository.save(new qaListRow("How's the measure?","It's 72cm."));
			
			//全ListRowを見る
			log.info("qalist found with findall(): ");
			log.info("---------------------------");
			for(qaListRow row : repository.findAll()) {
				log.info(row.toString());
			}
			log.info("");
			//AnswerにItが含まれる奴を拾う？
			log.info("qalist found with findByAnswer('It'):");
			log.info("-------------------------------------");
			repository.findByAnswer("It").forEach(bauer -> { log.info(bauer.toString());});
			
		};
	}
	*/

}
