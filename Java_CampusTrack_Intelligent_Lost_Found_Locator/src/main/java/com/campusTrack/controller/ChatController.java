package com.campusTrack.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusTrack.entity.ChatMessage;

@RestController
@RequestMapping("/lostfound/")
public class ChatController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	private final Set<String> onlineUsers = Collections.synchronizedSet(new HashSet<>());
	
	private final Map<String,String> sessionIdToUser = Collections.synchronizedMap(new HashMap<>());
	
	@GetMapping("/users")
	public Set<String> getOnlineUsers(){
		return onlineUsers;
	}
	
	@MessageMapping("/register")
	public void register(ChatMessage message, StompHeaderAccessor headerAccessor) {
		String sessionId = headerAccessor.getSessionId();
		String username = message.getSender();
		
		if(username != null && !username.trim().isEmpty()) {
			onlineUsers.add(username);
			sessionIdToUser.put(sessionId, username);
			broadcastUserList();
		}
		
	}
	
	@MessageMapping("/sendMessage")
	public void sendMessage(ChatMessage message) {
		messagingTemplate.convertAndSend("/topic/messages",message);
	}
	
	
	public void removeUser(String sessionId) {
		String username = sessionIdToUser.get(sessionId);
		if(username != null) {
			onlineUsers.remove(username);
			sessionIdToUser.remove(sessionId);
			broadcastUserList();
		}
	}
	
//	public void removeUser(String sessionId) {
//		String username = sessionIdToUser.get(sessionId);
//		if (username != null) {
//			sessionIdToUser.remove(sessionId);
//
//			// check if same user has another active session
//			boolean stillOnline = sessionIdToUser.containsValue(username);
//
//			if (!stillOnline) {
//				onlineUsers.remove(username);
//			}
//
//			broadcastUserList();
//		}
//	}

	
	
	private void broadcastUserList() {
		messagingTemplate.convertAndSend("/topic/users", onlineUsers);
	}
	
	
}
