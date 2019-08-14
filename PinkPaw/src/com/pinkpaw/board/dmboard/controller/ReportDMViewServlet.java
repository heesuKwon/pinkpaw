package com.pinkpaw.board.dmboard.controller;

import java.io.IOException;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.dmboard.model.service.ReportDMService;
import com.pinkpaw.board.dmboard.model.vo.*;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/dm/reportDMView")
public class ReportDMViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDMViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파리미터
		int DmNo  = Integer.parseInt(request.getParameter("dmNo"));
		//2.비지니스로직 호출
		
		DM DM = new ReportDMService().SelectOne(DmNo);
		
		
		
		request.setAttribute("DM", DM);
		String view = "/WEB-INF/views/board/dm/reportDMView.jsp";
		
		//게시글 가져오기에 실패한경우
		if(DM == null){
			request.setAttribute("msg", "조회한 쪽지가 존재하지 않습니다.");
			request.setAttribute("loc", "/board/dm/reportDMList");
			view = "/WEB-INF/views/common/msg.jsp";
		}
		
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/board/dm/reportDMView.jsp")
			   .forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
