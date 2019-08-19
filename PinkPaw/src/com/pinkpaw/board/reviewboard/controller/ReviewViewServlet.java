package com.pinkpaw.board.reviewboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		System.out.println("reviewBoardNo@ReviewViewServlet="+reviewNo);
		
		//2.업무로직
		//조회수 증가
		//쿠키검사
		Cookie[] cookies = request.getCookies();
		String boardCookieVal = "";
		boolean hasRead = false;
		if(cookies != null) {
			for(Cookie c: cookies) {
				String name = c.getName();
				String value = c.getValue();
				if("reivewCookie".equals(name)) {
					boardCookieVal = value;
					if(value.contains("|"+reviewNo+"|")) {
						hasRead = true;
						break;
					}
				}
			}
		}
		
		//현재 게시글을 최초로 읽는다면.   
		if(!hasRead) {
			Cookie boardCookie 
			= new Cookie("reivewCookie", boardCookieVal+"|"+reviewNo+"|");
			//setMaxAge를 생략하면, 영속한다.
			boardCookie.setPath(request.getContextPath()+"/board");
			
			response.addCookie(boardCookie);
		}
		
		ReviewBoard reviewBoard = new ReviewService().selectOne(reviewNo, hasRead);
		System.out.println("reviewBoard@ReviewViewServlet="+reviewBoard);
		
		List<BoardComment> boardCommentList = new ReviewCommentService().selectBoardCommentList(reviewNo);
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
