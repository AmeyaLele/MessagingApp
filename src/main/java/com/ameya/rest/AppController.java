package com.ameya.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ameya.model.Message;
import com.ameya.model.MessageRepository;

@RestController
public class AppController {

	
	@RequestMapping("/")
	private String index() {
		return "Welcome to Spring Boot enabled messaging!!";
	}
}
