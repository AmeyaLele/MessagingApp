package com.ameya.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ameya.model.Message;
import com.ameya.model.MessageDTO;
import com.ameya.model.MessageRepository;

@Repository
public class MessageService implements IMessageService {

	@Autowired
	MessageRepository repository;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public MessageDTO sendMessages(MessageDTO messageDto) {
		Message message = convertToEntity(messageDto);
		Message persistedMsg = repository.save(message);
		MessageDTO savedMessageDto = convertToDto(persistedMsg);
		return savedMessageDto;
	}

	@Override
	public List<MessageDTO> getMessageByRecepient(String recepient) {

		List<Message> receivedMsgs = repository.getMessageByRecepient(recepient);
		List<MessageDTO> receivedMsgDto = receivedMsgs.stream().map(msg -> convertToDto(msg))
				.collect(Collectors.toList());

		return receivedMsgDto;
	}

	@Override
	public List<MessageDTO> getMessageBySender(String sender) {

		List<Message> sentMsgs = repository.getMessageBySender(sender);
		List<MessageDTO> sentMsgDto = sentMsgs.stream().map(msg -> convertToDto(msg))
				.collect(Collectors.toList());
		return sentMsgDto;
	}

	private Message convertToEntity(MessageDTO messageDto) {

		Message message = modelMapper.map(messageDto, Message.class);
		message.setTimeStamp(new Timestamp(System.currentTimeMillis()));
		return message;
	}

	private MessageDTO convertToDto(Message message) {

		MessageDTO messageDto = modelMapper.map(message, MessageDTO.class);
		return messageDto;
	}

	

}
