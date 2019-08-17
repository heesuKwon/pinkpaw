package com.pinkpaw.board.missingboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.missingboard.model.exception.MissingException;
import com.pinkpaw.board.missingboard.model.service.MissingService;
import com.pinkpaw.board.missingboard.model.vo.MissingBoard;

/**
 * Servlet implementation class BoardUpdateFormServlet
 */
@WebServlet("/board/missingUpdateForm")
public class BoardUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터핸들링
		int boardNo;
		try {
			boardNo = Integer.parseInt(request.getParameter("missingNo"));			
		} catch (NumberFormatException e) {
			throw new MissingException("유효하지 않은 게시글 요청입니다.");
		}
		//2.업무로직
		MissingBoard b = new MissingService().selectOne(boardNo);
		
		//3.view단처리
		request.setAttribute("board", b);
		request.getRequestDispatcher("/WEB-INF/views/board/community/missing/missingUpdate.jsp")
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
