package com.ameya.model;

import java.sql.Timestamp;

public class MessageDTO {

	private Long id;
	private String sender;
	private String recepient;
	private String message;
	private Timestamp dom;

	public String getSender() {
		return sender;
	}

	public String getRecepient() {
		return recepient;
	}

	public String getMessage() {
		return message;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setRecepient(String recepient) {
		this.recepient = recepient;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDom(Timestamp dom) {
		this.dom = dom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
