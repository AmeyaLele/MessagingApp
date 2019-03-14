package com.ameya.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.ameya.*"})
public class MyApp {

	public static void main(String[] args) {

		SpringApplication.run(MyApp.class, args);
	}

}
