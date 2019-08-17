package com.pinkpaw.board.reviewboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.exception.BoardException;
import com.pinkpaw.board.reviewboard.model.service.ReviewService;
import com.pinkpaw.board.reviewboard.model.vo.ReviewBoard;

/**
 * Servlet implementation class ReviewUpdateServlet
 */
@WebServlet("/board/review/reviewUpdate")
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 핸들링
		int reviewNo;
		try{
			reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		} catch(Exception e){
			throw new BoardException("유효하지 않은 게시글 요청입니다.");
		}

		//2.업무로직
		ReviewBoard rb = new ReviewService().selectOne(reviewNo);

		//3.view단 처리
		request.setAttribute("reviewBoard", rb);
		request.getRequestDispatcher("/WEB-INF/views/board/community/review/reviewUpdate.jsp")
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
