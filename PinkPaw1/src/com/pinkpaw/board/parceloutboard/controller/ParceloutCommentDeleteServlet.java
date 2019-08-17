package com.pinkpaw.board.parceloutboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.parceloutboard.model.service.ParceloutService;

/**
 * Servlet implementation class ParceloutCommentDeleteServlet
 */
@WebServlet("/board/parceloutBoard/ParceloutCommentDelete")
public class ParceloutCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParceloutCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	int parceloutNo = Integer.parseInt(request.getParameter("parceloutNo"));
	int boardCommentNo = Integer.parseInt(request.getParameter("del"));
	int result = new ParceloutService().deleteParceloutComment(boardCommentNo);
	
	String view = "/WEB-INF/views/common/msg.jsp";
	String msg = "";
	String loc = "/board/parcelout/parceloutView?parceloutNo="+parceloutNo;
	
	if(result > 0) {
		msg = "댓글 삭제 성공!";
	}else {
		msg = "댓글 삭제 실패!";
	}
	
	request.setAttribute("msg", msg);
	request.setAttribute("loc", loc);
	
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
