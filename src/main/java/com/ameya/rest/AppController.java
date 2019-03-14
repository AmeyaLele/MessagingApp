package com.ameya.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@RequestMapping("/")
	private String index() {
		return "Welcome to Spring Boot enabled messaging!!";
	}
}
