package com.pinkpaw.chat.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.pinkpaw.member.model.vo.Member;

/**
 * Configurator는 Websocket연결시 필요한 설정정보를 가진 클래스 
 *	
 * HandshakeRequest: 최초연결시 요청객체
 * HandshakeResponse: 최초연결시 응답객체
 *
 */
public class HelloWebSocketConfigurator 
	extends Configurator{

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, 
								HandshakeRequest request, 
								HandshakeResponse response) {
		HttpSession session = (HttpSession)request.getHttpSession();
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn"); 
		String userId = memberLoggedIn.getMemberId();
		
		
		sec.getUserProperties().put("userId", userId);
		System.out.println("config: "+userId+"작업!!!");
	}
	
	
}
