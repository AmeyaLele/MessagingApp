package com.ameya.rest;

import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ameya.model.Message;
import com.ameya.model.MessageDTO;
import com.ameya.model.MessageRepository;
import com.ameya.service.IMessageService;

@RestController
public class AppController {

	@Autowired
	IMessageService service;

	@RequestMapping("/")
	private ResponseEntity<String> index() {
		return new ResponseEntity<String>("Welcome to Spring Boot enabled messaging!!", HttpStatus.OK);
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	private ResponseEntity<Object> send(@RequestBody MessageDTO messageDTO) {

		MessageDTO savedMessageDto = service.sendMessages(messageDTO);
		return new ResponseEntity<Object>(savedMessageDto, HttpStatus.CREATED);
	}

	@RequestMapping("/receiveMsgs")
	private ResponseEntity<Object> receive(HttpServletRequest request) // Using HttpServletReq
	{
		String recepient = request.getParameter("recepient");

		if (!StringUtils.isEmpty(recepient)) {
			List<MessageDTO> receivedMsgDto = service.getMessageByRecepient(recepient);
			return new ResponseEntity<Object>(receivedMsgDto, HttpStatus.FOUND);
		}

		return new ResponseEntity<Object>("No recepient passed", HttpStatus.BAD_REQUEST);

	}

	@RequestMapping("/sentMsgs")
	private ResponseEntity<Object> sent(HttpServletRequest request) // Using HttpServletReq
	{
		String sender = request.getParameter("sender");

		if (!StringUtils.isEmpty(sender)) {
			
			List<MessageDTO> sentMsgDto = service.getMessageBySender(sender);
			return new ResponseEntity<Object>(sentMsgDto, HttpStatus.FOUND);
		}

		return new ResponseEntity<Object>("No sender passed", HttpStatus.BAD_REQUEST);

	}

}
