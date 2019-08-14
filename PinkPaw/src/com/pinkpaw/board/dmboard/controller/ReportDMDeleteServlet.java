package com.pinkpaw.board.dmboard.controller;

import java.io.File;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.dmboard.model.service.ReportDMService;
//import com.pinkpaw.board.model.service.NoticeBoardService;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/board/dm/reportDMDelete")
public class ReportDMDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDMDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.전송값 꺼내서 변수에 기록하기.
		int DmNo = Integer.parseInt(request.getParameter("DmNo"));

		//2.서비스로직호출
		int result = new ReportDMService().deleteReportDM(DmNo);
		
		//3. 받은 결과에 따라 뷰페이지 내보내기
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/dm/reportDMList";

		if(result>0)
			msg = "쪽지 삭제 성공!";
			
		else 
			msg = "쪽지 삭제 실패!";	
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
