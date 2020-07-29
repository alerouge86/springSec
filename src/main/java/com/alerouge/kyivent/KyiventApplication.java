package com.alerouge.kyivent;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KyiventApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyiventApplication.class, args);
	}

	
	// TODO: capire come gestire questo problema: 
	// commentato: funziona il LocalTime.now() ma scrive errato su DB
	// senza commento: viceversa
	
//	@PostConstruct
//    public void init(){
//      // Setting Spring Boot SetTimeZone
//      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//    }

}
