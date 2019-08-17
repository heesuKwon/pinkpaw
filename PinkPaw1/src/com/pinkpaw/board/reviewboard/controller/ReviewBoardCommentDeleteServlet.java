package com.pinkpaw.board.reviewboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.service.ReviewCommentService;


/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/review/reviewBoardCommentDelete")
public class ReviewBoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewBoardCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 핸들링
		int boardCommentNo = Integer.parseInt(request.getParameter("boardCommentNo"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		System.out.println("boardCommentNo@ReviewBoardCommentDeleteServlet="+boardCommentNo);
		
		//2.업무로직
		//댓글인 경우 boardCommentNo인 것과 boardCommentRef가 boardCommentNo와 같은 것을 같이 지움.
		int result = new ReviewCommentService().deleteBoardComment(boardCommentNo);
		
		//3.view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/review/reviewView?reviewNo="+boardNo;
		
		if(result>0) {
			msg = "삭제에 성공하였습니다.";
		} else{
			msg = "삭제에 실패하였습니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view)
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
