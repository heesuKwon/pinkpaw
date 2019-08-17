package com.pinkpaw.board.noticeboard.controller;

import java.io.File;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.pinkpaw.board.noticeboard.model.service.NoticeBoardService;
import com.pinkpaw.board.noticeboard.model.vo.NoticeBoard;
import com.pinkpaw.common.util.MvcRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.pinkpaw.board.noticeboard.model.vo.*;

/**
 * Servlet implementation class BoardFormEndServlet
 */
@WebServlet("/board/notice/noticeBoardFormEnd")
public class NoticeBoardFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeWriter = request.getParameter("noticeWriter");
		String noticeContent = request.getParameter("noticeContent");
		
		NoticeBoard b = new NoticeBoard();
		b.setNoticeTitle(noticeTitle);
		b.setNoticeWriter(noticeWriter);
		b.setNoticeContent(noticeContent);
		
		//2.업무로직
		int result = new NoticeBoardService().insertBoard(b);
		
		String msg = "";
		String loc = "/board/notice/noticeBoardList";

		if(result>0) {
			msg = "공지사항 등록성공!";
			loc = "/board/notice/noticeBoardView?noticeNo="+result;
		}
		else {
			msg = "공지사항 등록실패!";
		}
		
		//3.view단 처리
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
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
