package com.ameya.smokeTests;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.ameya.starter.MyApp;

@RunWith(SpringRunner.class) //Without this, TRT wont be initialised
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes=MyApp.class)
public class HttpRequestTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	public void testDefaultMessage()
	{
		System.out.println("Hitting random port -"+port);
		assertThat(restTemplate.getForObject("http://localhost:"+port, String.class))
		.contains("Welcome to Spring Boot enabled messaging!!");
	}
	
}
