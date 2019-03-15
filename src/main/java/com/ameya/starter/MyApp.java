package com.ameya.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ameya.model.MessageRepository;

@SpringBootApplication
@ComponentScan(basePackages= {"com.ameya.*"})
@EnableJpaRepositories(basePackages= {"com.ameya.model"})
public class MyApp {
	
	public static void main(String[] args) {

		SpringApplication.run(MyApp.class, args);
	}

	
	@Bean
	CommandLineRunner commandLineRunner(MessageRepository rep)
	{
		return (args) -> {
			System.out.println("Inside CLR");
		};
	}
}
