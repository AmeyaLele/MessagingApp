package com.ameya.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MESSAGETEMPLATE", schema="MSGSCHEMA")
public class Message {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String sender;
	private String recepient;
	private String message;
	private Timestamp dom;

	public Message() {
		super();
	}

	public Message(String sender, String recepient, String message, Timestamp dom) {
		super();
		this.sender = sender;
		this.recepient = recepient;
		this.message = message;
		this.dom = dom;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecepient() {
		return recepient;
	}

	public void setRecepient(String recepient) {
		this.recepient = recepient;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getTimeStamp() {
		return dom;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.dom = timeStamp;
	}

	@Override
	public String toString() {
		return String.format("Message [sender=%s, recepient=%s, message=%s, dom=%1$TD %1$TT]", sender, recepient,
				message, dom);
	}
}
