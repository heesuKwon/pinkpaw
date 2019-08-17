package com.pinkpaw.board.freeboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.freeboard.model.exception.FreeBoardException;
import com.pinkpaw.board.freeboard.model.service.FreeBoardService;
import com.pinkpaw.board.freeboard.model.vo.FreeBoard;


@WebServlet("/board/community/free/FreeUpdateForm")
public class FreeUpdateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public FreeUpdateForm() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 핸들링
		int freeNo;
		
		try {
			freeNo = Integer.parseInt(request.getParameter("freeNo"));			
		} catch (NumberFormatException e) {
			throw new FreeBoardException("유효하지 않은 게시글 요청입니다.");
		}
		//2.업무 로직
		FreeBoard freeBoard = new FreeBoardService().selectOneFreeBoard(freeNo);

		//3.view단 처리
		request.setAttribute("freeBoard", freeBoard);
		
		String view = "/WEB-INF/views/board/community/free/freeUpdate.jsp";
		
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
