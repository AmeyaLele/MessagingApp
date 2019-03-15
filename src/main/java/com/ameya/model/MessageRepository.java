package com.ameya.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface MessageRepository extends CrudRepository<Message, Long> {

	String getMessageByRecepient(String recepient);
}
