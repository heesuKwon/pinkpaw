package com.pinkpaw.board.missingboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.missingboard.model.service.MissingService;
import com.pinkpaw.board.missingboard.model.vo.MissingBoard;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/missingView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//1.파리미터 글번호
		
		int boardNo  = Integer.parseInt(request.getParameter("missingNo"));
//		
//		//2.비지니스로직 호출
//		//boardCookie=|3||5||100||40|
//		//쿠키검사
//		Cookie[] cookies = request.getCookies();
//		String boardCookieVal = "";
		boolean hasRead = false;
//		if(cookies != null) {
//			for(Cookie c: cookies) {
//				String name = c.getName();
//				String value = c.getValue();
//				if("boardCookie".equals(name)) {
//					boardCookieVal = value;
//					if(value.contains("|"+boardNo+"|")) {
//						hasRead = true;
//						break;
//					}
//				}
//			}
//		}
//		
//		//현재 게시글을 최초로 읽는다면.   
//		if(!hasRead) {
//			Cookie boardCookie 
//				= new Cookie("boardCookie", boardCookieVal+"|"+boardNo+"|");
//			//setMaxAge를 생략하면, 영속한다.
//			boardCookie.setPath(request.getContextPath()+"/board");
//			
//			response.addCookie(boardCookie);
//		}
//		
		//new MissingService().increaseReadCount(boardNo);
		MissingBoard board = new MissingService().selectOne(boardNo, hasRead);
		List<BoardComment> commentList = new MissingService().selectCommentList(boardNo);
		System.out.println("servlet:"+board);
		
		request.setAttribute("board", board);
		request.setAttribute("commentList", commentList);
//		
		String view = "/WEB-INF/views/board/community/missing/missingView.jsp";
//		
//		//게시글 가져오기에 실패한경우
		if(board == null){
			request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			request.setAttribute("loc", "/board/missingList");
			view = "/WEB-INF/views/common/msg.jsp";
		}
		request.getRequestDispatcher("/WEB-INF/views/board/community/missing/missingView.jsp")
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
