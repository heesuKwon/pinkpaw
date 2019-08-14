
package com.pinkpaw.board.noticeboard.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.pinkpaw.board.noticeboard.model.service.NoticeBoardService;
import com.pinkpaw.board.noticeboard.model.vo.NoticeBoard;

/**
 * Servlet implementation class BoardFormEndServlet
 */
@WebServlet("/board/notice/noticeBoardUpdateEnd")
public class NoticeBoardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeBoardUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// 1. 인코딩
				
		request.setCharacterEncoding("UTF-8");

		// 파라미터
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeWriter = request.getParameter("noticeWriter");
		String noticeContent = request.getParameter("noticeContent");
		
		
//		System.out.println("서블릿 넘버확인@@@@@@"+noticeNo);
//		System.out.println("서블릿 타이틀확인@@@@@@"+noticeTitle);
//		System.out.println("서블릿 작성자확인@@@@@@"+noticeWriter);
//		System.out.println("서블릿 내용확인@@@@@@"+noticeContent);
		
		NoticeBoard b = new NoticeBoard();
		b.setNoticeNo(noticeNo);
		b.setNoticeTitle(noticeTitle);
		b.setNoticeWriter(noticeWriter);
		b.setNoticeContent(noticeContent);
		
		
		//2. 비지니스로직 호출
		int result = new NoticeBoardService().updateBoard(b);
		
		//3. 처리결과에 따른 view단에 처리위임.
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		//javascript/html에서 사용할 url은 contextPath를 포함한다.
		String loc = "/board/notice/noticeBoardList";

		if(result>0){
			msg = "공지사항 등록 성공!";
			loc = "/board/notice/noticeBoardView?noticeNo="+noticeNo;
		}
		else {
			msg = "공지사항 등록 실패!";				
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}