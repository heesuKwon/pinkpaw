package com.pinkpaw.board.noticeboard.controller;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.noticeboard.model.exception.BoardException;
import com.pinkpaw.board.noticeboard.model.service.NoticeBoardService;
import com.pinkpaw.board.noticeboard.model.vo.NoticeBoard;

/**
 * Servlet implementation class BoardUpdateFormServlet
 */
@WebServlet("/board/notice/noticeBoardUpdateForm")
public class NoticeBoardUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeBoardUpdateFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터핸들링
		int noticeNo;
		try {
			noticeNo = Integer.parseInt(request.getParameter("noticeNo"));			
		} catch (NumberFormatException e) {
			throw new BoardException("유효하지 않은 게시글 요청입니다.");
		}
		//2.업무로직
		NoticeBoard b = new NoticeBoardService().SelectOne(noticeNo);
		
		//3.view단처리
		request.setAttribute("NoticeBoard", b);
		request.getRequestDispatcher("/WEB-INF/views/board/notice/noticeBoardUpdate.jsp")
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
