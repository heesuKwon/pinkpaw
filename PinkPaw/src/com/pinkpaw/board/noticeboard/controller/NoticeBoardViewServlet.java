package com.pinkpaw.board.noticeboard.controller;

import java.io.IOException;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.noticeboard.model.service.NoticeBoardService;
import com.pinkpaw.board.noticeboard.model.vo.NoticeBoard;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/notice/noticeBoardView")
public class NoticeBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeBoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파리미터 글번호
		int noticeNo  = Integer.parseInt(request.getParameter("noticeNo"));
		
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
					if(value.contains("|"+noticeNo+"|")) {
						hasRead = true;
						break;
					}
				}
			}
		}
		
		//현재 게시글을 최초로 읽는다면.   
		if(!hasRead) {
			Cookie boardCookie 
				= new Cookie("boardCookie", boardCookieVal+"|"+noticeNo+"|");
			//setMaxAge를 생략하면, 영속한다.
			boardCookie.setPath(request.getContextPath()+"/notice");
			
			response.addCookie(boardCookie);
		}
		
		NoticeBoard NoticeBoard = new NoticeBoardService().SelectOne(noticeNo, hasRead);
		
		
		
		request.setAttribute("NoticeBoard", NoticeBoard);
		String view = "/WEB-INF/views/board/notice/noticeBoardView.jsp";
		
		//게시글 가져오기에 실패한경우
		if(NoticeBoard == null){
			request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			request.setAttribute("loc", "/board/notice/noticeBoardList");
			view = "/WEB-INF/views/common/msg.jsp";
		}
		
		
//		System.out.println("서블릿확인-----------------------------------"+comment);
		
		
		request.getRequestDispatcher("/WEB-INF/views/board/notice/noticeBoardView.jsp")
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
