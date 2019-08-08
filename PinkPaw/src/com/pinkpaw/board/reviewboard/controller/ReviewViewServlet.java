package com.pinkpaw.board.reviewboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.service.ReviewCommentService;
import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.reviewboard.model.service.ReviewService;
import com.pinkpaw.board.reviewboard.model.vo.ReviewBoard;

/**
 * Servlet implementation class ReviewViewServlet
 */
@WebServlet("/board/review/reviewView")
public class ReviewViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 핸들링
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println("reviewBoardNo@ReviewViewServlet="+boardNo);
		
		//2.업무로직
		ReviewBoard reviewBoard = new ReviewService().selectOne(boardNo);
		System.out.println("reviewBoard@ReviewViewServlet="+reviewBoard);
		
		List<BoardComment> boardCommentList = new ReviewCommentService().selectBoardCommentList(boardNo);
		System.out.println("boardCommentList@ReviewViewServlet="+boardCommentList.toString());
		
		//3.view단 처리
		request.setAttribute("reviewBoard", reviewBoard);
		request.setAttribute("boardCommentList", boardCommentList);
		
		request.getRequestDispatcher("/WEB-INF/views/board/community/review/reviewView.jsp")
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
