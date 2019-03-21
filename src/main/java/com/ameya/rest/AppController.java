package com.ameya.rest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ameya.model.Message;
import com.ameya.model.MessageDTO;
import com.ameya.model.MessageRepository;
import com.ameya.service.IMessageService;

@RestController("/messaging")
public class AppController {

	@Autowired
	IMessageService service;

	@RequestMapping("/messaging/home")
	private ResponseEntity<String> index() {
		return new ResponseEntity<String>("Welcome to Spring Boot enabled messaging!!", HttpStatus.OK);
	}

	@RequestMapping(value = "/messaging/send", method = RequestMethod.POST)
	private ResponseEntity<Object> send(@RequestBody MessageDTO messageDTO) {

		MessageDTO savedMessageDto = service.sendMessages(messageDTO);
		return new ResponseEntity<Object>(savedMessageDto, HttpStatus.CREATED);
	}

	@RequestMapping("/messaging/receiveMsgs")
	private ResponseEntity<Object> receive(HttpServletRequest request, HttpServletResponse res)
			throws MissingServletRequestParameterException // Using HttpServletReq
	{
		String recepient = request.getParameter("recepient");

		if (!StringUtils.isEmpty(recepient)) {
			List<MessageDTO> receivedMsgDto = service.getMessageByRecepient(recepient);
			res.setHeader("last-accessed", new Timestamp(System.currentTimeMillis()).toString());
			return new ResponseEntity<Object>(receivedMsgDto, HttpStatus.FOUND);
		}

		return new ResponseEntity<Object>("No recepient passed", HttpStatus.BAD_REQUEST);

	}

	@RequestMapping("/messaging/sentMsgs")
	private ResponseEntity<Object> sent(HttpServletRequest request) // Using HttpServletReq
	{
		String sender = request.getParameter("sender");

		if (!StringUtils.isEmpty(sender)) {

			List<MessageDTO> sentMsgDto = service.getMessageBySender(sender);
			return new ResponseEntity<Object>(sentMsgDto, HttpStatus.FOUND);
		}

		return new ResponseEntity<Object>("No sender passed", HttpStatus.BAD_REQUEST);

	}

	@RequestMapping("/messaging/receiveByDate")
	private ResponseEntity<Object> getByDate(HttpServletRequest req) {

		String date = req.getParameter("date");
		String recepient= req.getParameter("recepient");

		if (!StringUtils.isEmpty(date) && !StringUtils.isEmpty(recepient)) {
			List<MessageDTO> receivedMsgsDto = service.getMessageByDateAndRecepient(new DateTime(date).toDate(),recepient);
			return new ResponseEntity<Object>(receivedMsgsDto, HttpStatus.FOUND);
		}

		return new ResponseEntity<Object>("No params provided", HttpStatus.BAD_REQUEST);

	}

}
