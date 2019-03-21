package com.ameya.service;

import java.util.Date;
import java.util.List;

import com.ameya.model.MessageDTO;

public interface IMessageService {

	/**
	 * Sends a message to a user
	 * 
	 * @param messageDto
	 * @return
	 */
	MessageDTO sendMessages(MessageDTO messageDto);

	/**
	 * Returns all messages for a user
	 * 
	 * @param recepient
	 * @return
	 */
	List<MessageDTO> getMessageByRecepient(String recepient);

	/**
	 * 
	 * Returns all messages sent by a user
	 * 
	 * @param sender
	 * @return
	 */
	List<MessageDTO> getMessageBySender(String sender);

	List<MessageDTO> getMessageByDateAndRecepient(Date dateTime, String recepient);
}
