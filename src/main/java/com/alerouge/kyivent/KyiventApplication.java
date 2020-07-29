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
}
