package com.ameya.sit;

import java.sql.Timestamp;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

//import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import com.ameya.model.Message;
import com.ameya.model.MessageRepository;
import com.ameya.starter.MyApp;

@RunWith(SpringRunner.class)
// @WebMvcTest(AppController.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = MyApp.class)
@AutoConfigureMockMvc
// @AutoConfigureTestDatabase
public class MessageApiTest {

	// @org.springframework.boot.test.context.TestConfiguration
	// static class TestConfiguration{
	// public IMessageService messageService() {
	// return new MessageService();
	// }
	//
	// ModelMapper modelmapper()
	// {
	// return new ModelMapper();
	// }
	// }

	@Autowired
	MessageRepository repository;

	@Autowired
	MockMvc mvc;

	@After
	public void resetDb() {
		repository.deleteAll();
	}

	@Test
	public void testSendMessage() throws Exception {
		Message msg = new Message("Ameya", "Suraj", "hello World", new Timestamp(System.currentTimeMillis()));
		mvc.perform(post("/send").contentType("application/json").content(JsonUtil.toJson(msg)));

		List<Message> msgs = (List<Message>) repository.findAll();
		assertThat(msgs).extracting(Message::getSender).contains("Ameya");

	}

	@Test
	public void testReceiveMessage() throws Exception {
		
		String sender="Ameya";
		String recepient="Suraj";
		String message="TestMessage";
		
		createSampleMessage(sender, recepient, message);

		MvcResult result=mvc.perform(get("/receiveMsgs")
				.contentType("application/json")
				.param("recepient", recepient))
				.andExpect(status().isFound())
				.andReturn();
		
		String output = result.getResponse().getContentAsString();
		assertTrue(output.contains(recepient));
	}
	
	@Test
	public void testSentMessage() throws Exception {
		
		String sender="Ameya";
		String recepient="Suraj";
		String message="TestMessage";
		
		createSampleMessage(sender, recepient, message);

		MvcResult result=mvc.perform(get("/sentMsgs")
				.contentType("application/json")
				.param("sender", sender))
				.andExpect(status().isFound())
				.andReturn();
		
		String output = result.getResponse().getContentAsString();
		assertTrue(output.contains(sender));
	}
	
	private void createSampleMessage(String sender,String recepient,String message) {
		Message sampleMessage = 
				new Message(sender, recepient, message, new Timestamp(System.currentTimeMillis()));
		repository.save(sampleMessage);		
	}
}
