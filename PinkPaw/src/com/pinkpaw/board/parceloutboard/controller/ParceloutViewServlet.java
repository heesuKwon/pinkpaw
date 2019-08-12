package com.pinkpaw.board.parceloutboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.parceloutboard.model.service.ParceloutService;
import com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard;

/**
 * Servlet implementation class ParceloutViewServlet
 */
@WebServlet("/board/community/parcelout/parceloutView")
public class ParceloutViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParceloutViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//인코딩
		request.setCharacterEncoding("UTF-8");
		
		//파라미터 핸들링
		int parceloutNo = Integer.parseInt(request.getParameter("parceloutNo"));
		System.out.println(parceloutNo);
		
		//2.비지니스로직 호출
				//boardCookie=|3||5||100||40|
				//쿠키검사
				Cookie[] cookies = request.getCookies();
				String boardCookieVal = "";
				boolean hasRead = false;
				if(cookies != null) {
					for(Cookie c: cookies) {
						String name = c.getName();
						String value = c.getValue();
						if("boardCookie".equals(name)) {
							boardCookieVal = value;
							if(value.contains("|"+parceloutNo+"|")) {
								hasRead = true;
								break;
							}
						}
					}
				}
				
				//현재 게시글을 최초로 읽는다면.   
				if(!hasRead) {
					Cookie boardCookie 
						= new Cookie("boardCookie", boardCookieVal+"|"+parceloutNo+"|");
					//setMaxAge를 생략하면, 영속한다.
					boardCookie.setPath(request.getContextPath()+"/board");
					
					response.addCookie(boardCookie);
				}
		
		ParceloutBoard p = new ParceloutService().selectOne(parceloutNo);
		List<BoardComment> commentList = new ParceloutService().selectCommentList(parceloutNo);
		
		System.out.println(p);
		request.setAttribute("p", p);
		request.setAttribute("commentList", commentList);
		
		String view = "/WEB-INF/views/board/community/parcelout/parceloutView.jsp";
		
		if(p == null) {
			request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			request.setAttribute("loc", "/board/community/parcelout/parceloutList");
			view = "/WEB-INF/views/common/msg.jsp";
		}
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
