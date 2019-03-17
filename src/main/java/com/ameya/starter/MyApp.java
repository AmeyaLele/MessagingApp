package com.ameya.starter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.ModelMap;

import com.ameya.model.MessageRepository;

@SpringBootApplication
@EntityScan(basePackages= {"com.ameya.model"})
@ComponentScan(basePackages= {"com.ameya.*"})
@EnableJpaRepositories(basePackages= {"com.ameya.model"})
public class MyApp {
	
	public static void main(String[] args) {

		SpringApplication.run(MyApp.class, args);
	}
	
	@Bean
	ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
