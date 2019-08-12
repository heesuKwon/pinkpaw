package com.pinkpaw.board.freeboard.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.service.FreeBoardCommentService;
import com.pinkpaw.board.common.model.vo.BoardComment;


@WebServlet("/board/community/free/freeBoardCommentInsert")
public class FreeBoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FreeBoardCommentInsertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//1.파라미터 핸들링
		int freeBoardRef = Integer.parseInt(request.getParameter("boardRef"));
		String freeBoardCommentWriter = request.getParameter("boardCommentWriter");
		String freeBoardCommentContent = request.getParameter("boardCommentContent");
		int freeBoardCommentLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		int freeBoardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));

		BoardComment boardComment = new BoardComment(0, freeBoardCommentLevel, freeBoardCommentWriter, freeBoardCommentContent,
													 freeBoardRef, freeBoardCommentRef, null);
		
		System.out.println("boardComment@servlet="+boardComment);

		//2.업무로직
		int result = new FreeBoardCommentService().insertFreeBoardComment(boardComment);

		//3.view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/community/free/freeView?freeNo="+freeBoardRef;

		if(result > 0) {
			msg = "댓글 등록 성공!";
		}
		else {
			msg = "댓글 등록 실패!";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
