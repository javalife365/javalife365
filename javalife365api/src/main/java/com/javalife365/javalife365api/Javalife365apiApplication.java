package com.javalife365.javalife365api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Javalife365apiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Javalife365apiApplication.class, args);
	}

}
