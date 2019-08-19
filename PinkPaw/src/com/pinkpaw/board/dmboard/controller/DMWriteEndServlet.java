package com.pinkpaw.board.dmboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.pinkpaw.board.dmboard.model.service.DMService;
import com.pinkpaw.board.dmboard.model.vo.DM;
import com.pinkpaw.chat.controller.HelloWebSocket;

/**
 * Servlet implementation class DMWriteEndServlet
 */
@WebServlet("/DMWriteEnd")
public class DMWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DMWriteEndServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//파라미터핸들링


		response.setContentType("application/json; charset=utf-8");

		//2.파라미터
		String dm = request.getParameter("dm");
		Map<String, Object> dmMap = new Gson().fromJson(dm, Map.class);
		System.out.println("dmMap="+dmMap);

		//3.업무로직
		Map<String, Session> clients
		= HelloWebSocket.clients;
		Set<String> userIdSet = clients.keySet();
		String result = "";

		

		String dmSender = (String)dmMap.get("receiver");
		String dmWriter = (String)dmMap.get("sender");

		String dmTitle = (String)dmMap.get("title");
		String dmContent = (String)dmMap.get("msg");

		DM d = new DM();

		d.setDmRecive(dmSender);
		d.setDmSend(dmWriter);
		d.setDmTitle(dmTitle);
		d.setDmContent(dmContent);
		int resul = new DMService().insertDM(d);
		
		System.out.println("result@servlet="+resul);

		
		//접속자 확인
		if(userIdSet.contains(dmMap.get("receiver"))) {
			Session receiver = clients.get(dmMap.get("receiver"));

			System.out.println("receive확인!:"+dmMap.get("receiver"));

			receiver.getBasicRemote()
			.sendText(dm);

			result = "DM 전송 성공!";
		}
		else {
			result = "해당 사용자가 접속중이 아닙니다.";
		}
		String close = "false";
		//4.view단
//		Map<String, String> resultMap 
//		= new HashMap<>();
//		resultMap.put("result", result);
//		resultMap.put("close", close);

		String msg = "";
		String loc = "dmWrite?memberId="+dmWriter;

		if(resul>0) {
			msg = "게시글 등록성공!";
			close = "true";
			System.out.println(msg);
			loc = "dmWrite?memberId="+dmWriter;

		}
		else {
			msg = "게시글 등록실패!";
			close = "false";
			System.out.println(msg);
		}

//		request.setAttribute("msg", msg);
//		request.setAttribute("loc", loc);
//		request.setAttribute("close", close);
		new Gson().toJson(close, response.getWriter());
//		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
