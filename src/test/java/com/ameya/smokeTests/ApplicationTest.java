package com.ameya.smokeTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.ameya.rest.AppController;
import com.ameya.starter.MyApp;

@RunWith(SpringRunner.class) //integrate Junit features with spring boot test
@SpringBootTest(classes=MyApp.class)
public class ApplicationTest {
	
	@Autowired
	AppController controller;
	
	@Test
	public void contextLoads() throws Exception
	{
		assertThat(controller).isNotNull();
	}

}
