package com.pinkpaw.chat.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(value="/chat/helloWebSocket",
				configurator=HelloWebSocketConfigurator.class)
public class HelloWebSocket {

	//현재 접속자를 관리할 Map객체
	public static Map<String, Session> clients
		= new HashMap<>();
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		//접속Map객체에 사용자 등록
		String userId = (String)config.getUserProperties().get("userId");
		clients.put(userId, session);
		
		//userId값을 Session객체의 userProperties에 저장
		session.getUserProperties()
			   .put("userId", userId);
		
		System.out.println("@OnOpen 현재접속자("+clients.size()+"): "+clients+"]");
		
		//다른 접속자에게 메세지 보냄
		Map<String, Object> map = new HashMap<>();
		map.put("type", "welcome");
		map.put("msg", userId+"님이 입장했습니다.");
		map.put("sender", userId);
		map.put("time", new Date().getTime());
		String jsonStr = new Gson().toJson(map);
		onMessage(jsonStr, session);
	}
	
	@OnMessage
	public void onMessage(String msg, Session session) {
		Map<String, Object> map 
			= new Gson().fromJson(msg, Map.class);
		String type = (String)map.get("type");
		
		//동기화처리: 처리도중 clients에 대한 변경 금지
		synchronized(clients) {
			//접속자 session리스트 생성
			Collection<Session> sessionList 
				= clients.values();
			
			for(Session sess: sessionList) {
				//본인인 경우, close메세지인 경우 
				//메세지 보내지 않음
				if("adieu".equals(type)
					&& sess.equals(session))
					continue;
				
				try {
					//Basic인터페이스를 구현객체가
					//실제 웹소켓연결에 대해서 읽기/쓰기 작업함.
					sess.getBasicRemote()
						.sendText(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			
		}
	}
	
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session) {
		String userId = (String)session.getUserProperties()
							   		   .get("userId");
		//사용자아이디
		Map<String, Object> map = new HashMap<>();
		map.put("type", "adieu");
		map.put("msg", userId+"님이 퇴장했습니다.");
		map.put("sender", userId);
		map.put("time", System.currentTimeMillis());
		onMessage(new Gson().toJson(map), session);
		
		//접속자목록에서 해당 사용자 세션을 제거
		clients.remove(userId);
		
		//현재 접속자
		System.out.println("@OnClose 현재접속자("+clients.size()+") : "+clients);
	}
}









