package com.pinkpaw.board.freeboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.service.FreeBoardCommentService;
import com.pinkpaw.board.freeboard.model.service.FreeBoardService;


@WebServlet("/board/community/free/freeBoardCommentDelete")
public class FreeBoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public FreeBoardCommentDeleteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 파라미터 핸들링
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		int freeBoardCommentNo = Integer.parseInt(request.getParameter("del"));
		System.out.println("freeNo="+freeNo+", del="+freeBoardCommentNo);
		
		//2. 업무 로직
		int result = new FreeBoardCommentService().deleteFreeBoardComment(freeBoardCommentNo);

		//3. view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/community/free/freeView?freeNo="+freeNo;

		if(result > 0)
			msg = "댓글 삭제 성공!";
		else 
			msg = "댓글 삭제 실패!";	

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		request.getRequestDispatcher(view).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
