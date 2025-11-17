package com.campusTrack.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Messages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int messagesId;
	
//	@Column(name = "sendUser_id", nullable = false)  
	@ManyToOne
	@JoinColumn(name = "sendUser_id")
	private Users sender;
	
//	@Column(name = "receiveUser_id", nullable = false)  
	@ManyToOne
	@JoinColumn(name = "receiveUser_id")
	private Users receiver;
	
	@Column(name = "context", columnDefinition = "TEXT NOT NULL")
	private String context;
	
	@Column(name = "timestamp", nullable = false)
	private LocalDateTime timestamp;
	
	@Column(name = "is_read" , nullable = false)
	private boolean is_read;

	public Messages() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Messages(int id,  String context,
			boolean is_read) {
//		Users sender_id, Users receiver_id,
		super();
		this.messagesId = id;
//		this.sender_id = sender_id;
//		this.receiver_id = receiver_id;
		this.context = context;
		this.timestamp = LocalDateTime.now();
		this.is_read = is_read;
	}



	public int getId() {
		return messagesId;
	}

	public void setId(int id) {
		this.messagesId = id;
	}

	public Users getSender_id() {
		return sender;
	}

	public void setSender_id(Users sender_id) {
		this.sender = sender_id;
	}

	public Users getReceiver_id() {
		return receiver;
	}

	public void setReceiver_id(Users receiver_id) {
		this.receiver = receiver_id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isIs_read() {
		return is_read;
	}

	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}

	@Override
	public String toString() {
		return "Messages [id=" + messagesId +  ", context="
				+ context + ", timestamp=" + timestamp + ", is_read=" + is_read + "]";
	}
//	, sender_id=" + sender_id +
//	", receiver_id=" + receiver_id +
	
	
//	do the flag table
	
	
	
	
}
