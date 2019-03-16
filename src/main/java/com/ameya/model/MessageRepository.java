package com.ameya.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface MessageRepository extends CrudRepository<Message, Long> {

	/**
	 * Returns all messages for a user
	 * @param recepient
	 * @return
	 */
	List<Message> getMessageByRecepient(String recepient);
	
	/**
	 * 
	 * Returns all messages sent by a user
	 * @param sender
	 * @return
	 */
	List<Message> getMessageBySender(String sender);
}
