package com.pinkpaw.board.freeboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.service.FreeBoardCommentService;
import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.freeboard.model.service.FreeBoardService;
import com.pinkpaw.board.freeboard.model.vo.FreeBoard;


@WebServlet("/board/community/free/freeView")
public class FreeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FreeViewServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		
		//2.업무 로직
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
					if(value.contains("|"+freeNo+"|")) {
						hasRead = true;
						break;
					}
				}
			}
		}
		   
		if(!hasRead) {
			Cookie boardCookie = new Cookie("boardCookie", boardCookieVal+"|"+freeNo+"|");
			boardCookie.setPath(request.getContextPath()+"/board");
			
			response.addCookie(boardCookie);
		}
		
		FreeBoard freeBoard= new FreeBoardService().selectOneFreeBoard(freeNo, hasRead);
		List<BoardComment> commentList = new FreeBoardCommentService().selectFreeCommentList(freeNo);
		
		request.setAttribute("freeBoard", freeBoard);
		request.setAttribute("commentList", commentList);
		
		String view = "/WEB-INF/views/board/community/free/freeView.jsp";
		
		// 게시글 가져오기 실패한 경우
		if(freeBoard == null){
			request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			request.setAttribute("loc", "/board/community/free/freeList");
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
